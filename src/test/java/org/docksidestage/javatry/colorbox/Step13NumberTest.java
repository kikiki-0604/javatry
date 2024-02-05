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

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;
import org.h2.util.StringUtils;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54 (includes)? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Integer result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(colorBoxSpace -> colorBoxSpace.getContent())
                .filter(colorBoxContent -> colorBoxContent != null && colorBoxContent instanceof Integer)
                .map(colorBoxContent -> Integer.parseInt(colorBoxContent.toString()))
                .filter(content -> content > 0 && content <= 54)
                .collect(Collectors.toList()).size();

                log(result);
    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     * 卍色々混ざってたらめんどくさい卍
     */
    public void test_countZeroToFiftyFour_Number() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        StringUtils.isNumber("");
        List<Object> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(colorBoxSpace -> colorBoxSpace.getContent())
                .filter(colorBoxContent -> colorBoxContent != null)
                .collect(Collectors.toList());
        log(result);
    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
//        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
//        String result = colorBoxList.stream()
//                .filter(colorBox -> {
//                    List<BoxSpace> spaceList = colorBox.getSpaceList().stream().filter(space -> {
//                        final Object content = space.getContent();
//                        return content != null && content instanceof Integer;
//                    }).collect(Collectors.toList());
//                    return !spaceList.isEmpty();
//                })
//                .max(colorBox -> colorBox.getSize().getWidth())
//
//                .flatMap(colorBox -> colorBox.getSpaceList().stream())
//                .map(colorBoxSpace,) -> colorBoxSpace.getContent().)
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String result = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream()
                        .anyMatch(space -> space.getContent() instanceof Integer)
                )
                .max(Comparator.comparingInt(box -> box.getSize().getWidth()))
                .map(colorBox -> colorBox.getColor().getColorName())
                .orElse("No such box");

        log(result);
    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        BigDecimal result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(colorBoxSpace -> colorBoxSpace.getContent())
                .filter(content -> content != null && content instanceof List)
                .flatMap(list -> ((List<?>) list).stream())
                .filter(list -> list instanceof BigDecimal)
                .map(list -> (BigDecimal)list)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        log(result);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Integer result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(colorBoxSpace -> colorBoxSpace.getContent())
                .filter(content -> content != null && content instanceof Map)
                .flatMap(mapContent -> ((Map<Object,Object>) mapContent).values().stream())
                .filter(value -> value instanceof Number)
                .map(value -> (Integer)value)
                .max(Comparator.comparing(value -> value))
                .orElseThrow(() -> new RuntimeException());

        log(result);
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Integer result = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("purple"))
                .flatMap(purpleBox -> purpleBox.getSpaceList().stream())
                .map(colorBoxSpace -> colorBoxSpace.getContent())
                .filter(content -> content != null && content instanceof Map)
                .flatMap(mapContent -> ((Map<?, ?>) mapContent).values().stream())
                .filter(value -> value instanceof Number)
                .mapToInt(value -> ((Number) value).intValue())
                .sum();

        log(result);

        assertEquals(550,result);
    }
}
