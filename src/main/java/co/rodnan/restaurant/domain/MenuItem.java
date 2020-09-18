package co.rodnan.restaurant.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@RequiredArgsConstructor
public class MenuItem {

    String name;
    CourseType type;
    BigDecimal price;

    public static MenuItem createSoup(String name) {
        return MenuItem.builder()
                .name(name)
                .type(CourseType.SOUP)
                .build();
    }

    public static MenuItem createSoup(String name, BigDecimal price) {
        return MenuItem.builder()
                .name(name)
                .price(price)
                .type(CourseType.SOUP)
                .build();
    }

    public static MenuItem createMainCourse(String name) {
        return MenuItem.builder()
                .name(name)
                .type(CourseType.MAIN_COURSE)
                .build();
    }

    public static MenuItem createMainCourse(String name, BigDecimal price) {
        return MenuItem.builder()
                .name(name)
                .price(price)
                .type(CourseType.MAIN_COURSE)
                .build();
    }

    public static MenuItem createMainCourseA(String name) {
        return MenuItem.builder()
                .name("A:" + name)
                .type(CourseType.MAIN_COURSE)
                .build();
    }

    public static MenuItem createMainCourseB(String name) {
        return MenuItem.builder()
                .name("B:" + name)
                .type(CourseType.MAIN_COURSE)
                .build();
    }

    public static MenuItem createDessert(String name) {
        return MenuItem.builder()
                .name(name)
                .type(CourseType.DESSERT)
                .build();
    }

}
