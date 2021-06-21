package com.patrushev.home_work_14.config;

import com.patrushev.home_work_14.model.h2.Author;
import com.patrushev.home_work_14.repository.h2.H2AuthorRepository;
import com.patrushev.home_work_14.repository.mongo.MongoAuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final H2AuthorRepository h2AuthorRepository;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoAuthorRepository mongoAuthorRepository;

    //TODO попробовать:
    // 1) поменять местами mongo и h2
    // 2) вместо h2 поднять postgres
    // 3) настроить перезапуск джобы из spring shell

    //ридер для получения объектов из базы h2
    @Bean
    public RepositoryItemReader<Author> h2RepositoryAuthorReader() {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.DESC);
        return new RepositoryItemReaderBuilder<Author>()
                .name("h2RepositoryAuthorReader")
                .repository(h2AuthorRepository)
                .pageSize(10)
                .methodName("findAll")
                .sorts(sortMap)
                .build();
    }

    //процессор для маппинга объектов из h2 в mongo
    @Bean
    public ItemProcessor<Author, com.patrushev.home_work_14.model.mongo.Author> h2ToMongoAuthorProcessor() {
        return author -> {
            log.info("попали в процессор вроде!");
            return com.patrushev.home_work_14.model.mongo.Author.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .build();
        };
    }

    //райтер для записи объектов в монгу
    @Bean
    public RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Author> mongoAuthorWriter() {
        return new RepositoryItemWriterBuilder<com.patrushev.home_work_14.model.mongo.Author>()
                .repository(mongoAuthorRepository)
                .methodName("save")
                .build();
    }

    //дальше описание шагов джобы
    @Bean
    public Step authorStep1(RepositoryItemReader<Author> h2RepositoryAuthorReader,
                            ItemProcessor<Author, com.patrushev.home_work_14.model.mongo.Author> h2ToMongoAuthorProcessor,
                            RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Author> mongoRepositoryAuthorWriter) {
        return stepBuilderFactory.get("authorStep1")
                .<Author, com.patrushev.home_work_14.model.mongo.Author> chunk(5)
                .reader(h2RepositoryAuthorReader)
                .processor(h2ToMongoAuthorProcessor)
                .writer(mongoRepositoryAuthorWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }
                    public void afterRead(Author o) {
                        log.info("прочитали автора: {}", o);
                        log.info("Конец чтения");
                    }
                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(List list) {
                        log.info("Начало записи");
                    }
                    public void afterWrite(List list) {
                        log.info("Конец записи");
                    }
                    public void onWriteError(Exception e, List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Author o) {
                        log.info("Начало обработки");
                    }
                    public void afterProcess(Author o, com.patrushev.home_work_14.model.mongo.Author o2) {
                        log.info("Конец обработки");
                    }
                    public void onProcessError(Author o, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {log.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {log.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {log.info("Ошибка пачки");}
                })
                .build();
    }

    //дальше описание джобы
    @Bean
    public Job importAuthorJob(Step authorStep1) {
        return jobBuilderFactory.get("migrateAuthorJob")
                .incrementer(new RunIdIncrementer())
                .flow(authorStep1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }
}
