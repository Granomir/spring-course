package com.patrushev.home_work_14.config;

import com.patrushev.home_work_14.model.h2.Author;
import com.patrushev.home_work_14.model.h2.Book;
import com.patrushev.home_work_14.model.h2.Genre;
import com.patrushev.home_work_14.repository.h2.H2AuthorRepository;
import com.patrushev.home_work_14.repository.h2.H2BookRepository;
import com.patrushev.home_work_14.repository.h2.H2GenreRepository;
import com.patrushev.home_work_14.repository.mongo.MongoAuthorRepository;
import com.patrushev.home_work_14.repository.mongo.MongoBookRepository;
import com.patrushev.home_work_14.repository.mongo.MongoGenreRepository;
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
    private final H2BookRepository h2BookRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final H2GenreRepository h2GenreRepository;
    private final H2AuthorRepository h2AuthorRepository;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoBookRepository mongoBookRepository;
    private final MongoGenreRepository mongoGenreRepository;
    private final MongoAuthorRepository mongoAuthorRepository;

    //TODO попробовать:
    // 1) поменять местами mongo и h2
    // 2) вместо h2 поднять postgres
    // 3) настроить перезапуск джобы из spring shell

    //ридер для получения авторов из базы h2
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

    //ридер для получения жанров из базы h2
    @Bean
    public RepositoryItemReader<Genre> h2RepositoryGenreReader() {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.DESC);
        return new RepositoryItemReaderBuilder<Genre>()
                .name("h2RepositoryGenreReader")
                .repository(h2GenreRepository)
                .pageSize(10)
                .methodName("findAll")
                .sorts(sortMap)
                .build();
    }

    //ридер для получения книг из базы h2
    @Bean
    public RepositoryItemReader<Book> h2RepositoryBookReader() {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.DESC);
        return new RepositoryItemReaderBuilder<Book>()
                .name("h2RepositoryBookReader")
                .repository(h2BookRepository)
                .pageSize(10)
                .methodName("findAll")
                .sorts(sortMap)
                .build();
    }

    //процессор для маппинга авторов из h2 в mongo
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

    //процессор для маппинга жанров из h2 в mongo
    @Bean
    public ItemProcessor<Genre, com.patrushev.home_work_14.model.mongo.Genre> h2ToMongoGenreProcessor() {
        return genre -> {
            log.info("попали в процессор вроде!");
            return com.patrushev.home_work_14.model.mongo.Genre.builder()
                    .id(genre.getId())
                    .name(genre.getName())
                    .build();
        };
    }

    //процессор для маппинга книг из h2 в mongo
    @Bean
    public ItemProcessor<Book, com.patrushev.home_work_14.model.mongo.Book> h2ToMongoBookProcessor() {
        return book -> {
            log.info("попали в процессор вроде!");
            return com.patrushev.home_work_14.model.mongo.Book.builder()
                    .id(book.getId())
                    .author(com.patrushev.home_work_14.model.mongo.Author.builder()
                            .id(book.getAuthor().getId())
                            .name(book.getAuthor().getName())
                            .build())
                    .genre(com.patrushev.home_work_14.model.mongo.Genre.builder()
                            .id(book.getGenre().getId())
                            .name(book.getGenre().getName())
                            .build())
                    .build();
        };
    }

    //райтер для записи авторов в монгу
    @Bean
    public RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Author> mongoAuthorWriter() {
        return new RepositoryItemWriterBuilder<com.patrushev.home_work_14.model.mongo.Author>()
                .repository(mongoAuthorRepository)
                .methodName("save")
                .build();
    }

    //райтер для записи жанров в монгу
    @Bean
    public RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Genre> mongoGenreWriter() {
        return new RepositoryItemWriterBuilder<com.patrushev.home_work_14.model.mongo.Genre>()
                .repository(mongoGenreRepository)
                .methodName("save")
                .build();
    }

    //райтер для записи книг в монгу
    @Bean
    public RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Book> mongoBookWriter() {
        return new RepositoryItemWriterBuilder<com.patrushev.home_work_14.model.mongo.Book>()
                .repository(mongoBookRepository)
                .methodName("save")
                .build();
    }

    //дальше описание шагов джобы
    @Bean
    public Step authorStep(RepositoryItemReader<Author> h2RepositoryAuthorReader,
                           ItemProcessor<Author, com.patrushev.home_work_14.model.mongo.Author> h2ToMongoAuthorProcessor,
                           RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Author> mongoRepositoryAuthorWriter) {
        return stepBuilderFactory.get("authorStep1")
                .<Author, com.patrushev.home_work_14.model.mongo.Author>chunk(5)
                .reader(h2RepositoryAuthorReader)
                .processor(h2ToMongoAuthorProcessor)
                .writer(mongoRepositoryAuthorWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        final List<Author> all = h2AuthorRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом чтения: {}", all);
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
                    public void beforeChunk(ChunkContext chunkContext) {
                        final List<Author> all = h2AuthorRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом пачки: {}", all);
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }

    //дальше описание шагов джобы
    @Bean
    public Step genreStep(RepositoryItemReader<Genre> h2RepositoryGenreReader,
                          ItemProcessor<Genre, com.patrushev.home_work_14.model.mongo.Genre> h2ToMongoGenreProcessor,
                          RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Genre> mongoRepositoryGenreWriter) {
        return stepBuilderFactory.get("genreStep")
                .<Genre, com.patrushev.home_work_14.model.mongo.Genre>chunk(5)
                .reader(h2RepositoryGenreReader)
                .processor(h2ToMongoGenreProcessor)
                .writer(mongoRepositoryGenreWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        final List<Genre> all = h2GenreRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом чтения: {}", all);
                        log.info("Начало чтения");
                    }

                    public void afterRead(Genre o) {
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
                    public void beforeProcess(Genre o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Genre o, com.patrushev.home_work_14.model.mongo.Genre o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Genre o, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        final List<Genre> all = h2GenreRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом пачки: {}", all);
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }

    //дальше описание шагов джобы
    @Bean
    public Step bookStep(RepositoryItemReader<Book> h2RepositoryBookReader,
                         ItemProcessor<Book, com.patrushev.home_work_14.model.mongo.Book> h2ToMongoBookProcessor,
                         RepositoryItemWriter<com.patrushev.home_work_14.model.mongo.Book> mongoRepositoryBookWriter) {
        return stepBuilderFactory.get("bookStep")
                .<Book, com.patrushev.home_work_14.model.mongo.Book>chunk(5)
                .reader(h2RepositoryBookReader)
                .processor(h2ToMongoBookProcessor)
                .writer(mongoRepositoryBookWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        final List<Book> all = h2BookRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом чтения: {}", all);
                        log.info("Начало чтения");
                    }

                    public void afterRead(Book o) {
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
                    public void beforeProcess(Book o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Book o, com.patrushev.home_work_14.model.mongo.Book o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Book o, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        final List<Book> all = h2BookRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом пачки: {}", all);
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }

    //дальше описание джобы
    @Bean
    public Job importAuthorJob(Step authorStep, Step genreStep, Step bookStep) {
        return jobBuilderFactory.get("migrateAuthorJob")
                .incrementer(new RunIdIncrementer())
                .flow(authorStep)
                .next(genreStep)
                .next(bookStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final List<Author> all = h2AuthorRepository.findAll();
                        log.info("проверяем наличие данных в базе H2 перед стартом джобы: {}", all);
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
