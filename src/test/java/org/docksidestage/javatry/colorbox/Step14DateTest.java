/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Date with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step14DateTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is date in color-boxes formatted as plus-separated (e.g. 2019+04+24)? <br>
     * (カラーボックスに入っている日付をプラス記号区切り (e.g. 2019+04+24) のフォーマットしたら？)
     */
    public void test_formatDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<String> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof Set)
                .map(dateContent -> dateContent.toString().replace("/","+"))
                .collect(Collectors.toList());
        log(result);
}

    /**
     * How is it going to be if the slash-separated date string in yellow color-box is converted to LocaDate and toString() is used? <br>
     * (yellowのカラーボックスに入っているSetの中のスラッシュ区切り (e.g. 2019/04/24) の日付文字列をLocalDateに変換してtoString()したら？)
     */
    public void test_parseDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<String> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof Set)
                .flatMap(setContentList -> ((Set<String>) setContentList).stream()
                        .map(setContent -> LocalDate.parse(setContent.replace('O', '0'), formatter).toString()))
                .collect(Collectors.toList());

        log(result);
    }

    /**
     * What is total of month numbers of date in color-boxes? <br>
     * (カラーボックスに入っている日付の月を全て足したら？)
     */
    public void test_sumMonth() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        int result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof Set)
                .flatMap(setContentList -> ((Set<String>) setContentList).stream()
                        .map(setContent -> LocalDate.parse(setContent.replace('O', '0'), formatter)))
                .mapToInt(LocalDate::getMonthValue)
                .sum();
        log(result);
    }

    /**
     * Add 3 days to second-found date in color-boxes, what day of week is it? <br>
     * (カラーボックスに入っている二番目に見つかる日付に3日進めると何曜日？)
     */
    public void test_plusDays_weekOfDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<String> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof Set)
                .flatMap(setContentList -> ((Set<String>) setContentList).stream()
                        .map(setContent -> LocalDate.parse(setContent.replace('O', '0'), formatter)))
                .map(setContent -> {
                    LocalDate date = setContent.plusDays(3);
                    String dayOfWeek = date.getDayOfWeek().toString();
                    return dayOfWeek;
                })
                .collect(Collectors.toList());

        log(result.get(1));

    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * How many days (number of day) are between two dates in yellow color-boxes? <br>
     * (yellowのカラーボックスに入っている二つの日付は何日離れている？)
     */
    public void test_diffDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<LocalDate> result = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(purpleBox -> purpleBox.getSpaceList().stream())
                .map(colorBoxSpace -> colorBoxSpace.getContent())
                .filter(content -> content != null && content instanceof Set)
                .flatMap(setContentList -> ((Set<String>) setContentList).stream()
                        .map(setContent -> LocalDate.parse(setContent.replace('O', '0'), formatter)))
                .collect(Collectors.toList());

        Long between = ChronoUnit.DAYS.between(result.get(0),result.get(1));

        log(between);

    }

    /**
     * Find LocalDate in yellow color-box,
     * and add same color-box's LocalDateTime's seconds as number of months to it,
     * and add red color-box's Long number as days to it,
     * and subtract the first decimal place of BigDecimal that has three(3) as integer in List in color-boxes from it,
     * What date is it? <br>
     * (yellowのカラーボックスに入っているLocalDateに、同じカラーボックスに入っているLocalDateTimeの秒数を月数として足して、
     * redのカラーボックスに入っているLong型を日数として足して、カラーボックスに入っているリストの中のBigDecimalの整数値が3の小数点第一位の数を日数として引いた日付は？)
     */
    public void test_birthdate() {
//        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//
//        List<LocalDate> result = colorBoxList.stream()
//                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
//                .flatMap(purpleBox -> purpleBox.getSpaceList().stream())
//                .map(colorBoxSpace -> colorBoxSpace.getContent())
//                .filter(content -> content != null && content instanceof Set)
//                .flatMap(setContentList -> ((Set<String>) setContentList).stream()
//                        .map(setContent -> LocalDate.parse(setContent.replace('O', '0'), formatter)))
//                .collect(Collectors.toList());
//

    }

    /**
     * What second is LocalTime in color-boxes? <br>
     * (カラーボックスに入っているLocalTimeの秒は？)
     */
    public void test_beReader() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Integer> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace instanceof DoorBoxSpace)
                .map(boxSpace -> (DoorBoxSpace)boxSpace)
                .map(doorBoxSpace -> {
                    doorBoxSpace.openTheDoor();
                    return doorBoxSpace.getContent();
                })
                .filter(content -> content != null && content instanceof LocalTime)
                .map(timeContent -> ((LocalTime) timeContent).toSecondOfDay())
                .collect(Collectors.toList());

        log(result);
    }
}
