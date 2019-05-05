package com.zdw.zms.test.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Book
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/3 11:48 AM
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "mappers.test.book")
@Data
public class Book {
    private String name;
    private double version;
    private double price;
    private List<String> others;
    private List<Book> books;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date publicationDate = new Date();

}
