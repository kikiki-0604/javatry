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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author your_name_here
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     *
     * ①カラーボックスを取得
     * ②取得したカラーボックスの各スペースを取得してフラットにする
     * ③フィルターで中身がnullでないものを取得する
     * ④取得した中身の文字列をソードして一番文字列の長いものを取得する
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Optional<Object> maxStr = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .max(Comparator.comparingInt(content -> content.toString().length()));

        log(maxStr);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxStrLength = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .mapToInt(content -> content.toString().length())
                .max()
                .orElse(0);

        log(maxStrLength);

        int minStrLength = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .mapToInt(content -> content.toString().length())
                .min()
                .orElse(0);

        log(minStrLength);

        int diff = maxStrLength - minStrLength;

        log(diff);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int firstMaxLength = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .mapToInt(content -> content.toString().length())
                .max()
                .orElse(0);

        int secondMaxLength = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .mapToInt(content -> content.toString().length())
                .filter(length -> length < firstMaxLength)
                .max()
                .orElse(0);

        Optional<Object> secondMaxStr = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.toString().length() == secondMaxLength)
                .findFirst();

        log(secondMaxStr);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sumLength = colorBoxList.stream()
                .flatMap(cb -> cb.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .mapToInt(content -> content.toString().length())
                .sum();

        log(sumLength);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Optional<String> optColorName =
                colorBoxList.stream().map(cb -> cb.getColor().getColorName()).max(Comparator.comparingInt(colorName -> colorName.length()));

        log(optColorName.get());
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Object> colorBoxContent2 = colorBoxList.stream().filter(colorBox -> {
            List<BoxSpace> waterContainsList = colorBox.getSpaceList().stream().filter(boxSpace -> {
                final Object content = boxSpace.getContent();
                return content != null && content.toString().startsWith("Water");
            }).collect(Collectors.toList());
            return !waterContainsList.isEmpty();
        }).map(colorBox -> colorBox.getColor().getColorName()).collect(Collectors.toList());

        log(colorBoxContent2);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Object> frontColorBox = colorBoxList.stream().filter(colorBox -> {
            List<BoxSpace> waterContainsList = colorBox.getSpaceList().stream().filter(boxSpace -> {
                final Object content = boxSpace.getContent();
                return content != null && content.toString().endsWith("front");
            }).collect(Collectors.toList());
            return !waterContainsList.isEmpty();
        }).map(colorBox -> colorBox.getColor().getColorName()).collect(Collectors.toList());

        log(frontColorBox);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        //        for (ColorBox colorBox : colorBoxList) {
        //            List<BoxSpace> frontContainsList = colorBox.getSpaceList().stream().filter(boxSpace -> {
        //                final Object content = boxSpace.getContent();
        //                return content != null && content.toString().endsWith("front");
        //            }).collect(Collectors.toList());
        //
        //            if (!frontContainsList.isEmpty()) {
        //                BoxSpace firstFrontBoxSpace = frontContainsList.get(0);
        //                String contentString = firstFrontBoxSpace.getContent().toString();
        //                int indexOfFront = contentString.lastIndexOf("front") + 1;
        //
        //                log(indexOfFront);
        //            }
        //        }

        Optional<Integer> result = colorBoxList.stream().flatMap(colorBox -> colorBox.getSpaceList().stream()).filter(boxSpace -> {
            final Object content = boxSpace.getContent();
            return content != null && content.toString().endsWith("front");
        }).findFirst().map(boxSpace -> {
            String contentString = boxSpace.getContent().toString();
            int indexOfFront = contentString.lastIndexOf("front") + 1;
            return indexOfFront;
        });

        Optional<Integer> result2 = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content.toString().endsWith("front"))
                .findFirst()
                .map(content -> content.toString().lastIndexOf("front") + 1);

        log(result2);
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            List<Integer> result = colorBoxList.stream()
                    .flatMap(colorBox -> colorBox.getSpaceList().stream())
                    .map(boxSpace -> boxSpace.getContent())
                    .filter(content -> content != null && content.toString().contains("ど"))
                    .map(doContent -> {
                        Integer firstDo = doContent.toString().indexOf("ど");
                        Integer secondDo = doContent.toString().indexOf("ど", firstDo + 1) + 1;
                        if (secondDo != -1) {
                            return secondDo;
                        } else {
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
            log(result);
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Character> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content.toString().endsWith("front"))
                .map(contentFirstLength -> contentFirstLength.toString().charAt(0))
                .collect(Collectors.toList());

        log(result);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Character> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content.toString().startsWith("Water"))
                .map(contentEnd -> {
                    Integer contentEndStrLength = contentEnd.toString().length();
                    char contentEndStr = contentEnd.toString().charAt(contentEndStrLength - 1);
                    return contentEndStr;
                })
                .collect(Collectors.toList());

        log(result);

        assertThat(result.get(0), is('t'));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Integer> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content.toString().contains("o"))
                .map(oStr -> oStr.toString().replace("o", "").length())
                .collect(Collectors.toList());

        log(result);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<String> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof File)
                .map(fileContent -> fileContent.toString().replace("/", "\\"))
                .collect(Collectors.toList());
        log(result);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Object result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof YourPrivateRoom.DevilBox)
                .map(devilBox ->{
                    try{
                        ((YourPrivateRoom.DevilBox) devilBox).open();
                        return ((YourPrivateRoom.DevilBox) devilBox).getText().length();
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .reduce(0,Integer::sum);
        log(result);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = whivalue ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Map> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof Map)
                .map(map -> (Map)map)
                .collect(Collectors.toList());
        log(result);

        result.forEach(map -> {
            log("map:");
            map.forEach((key, value) -> {
                log(key + " = " + value + ";");
            });
        });
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<Map> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null && content instanceof Map)
                .map(map -> (Map)map)
                .collect(Collectors.toList());

        result.forEach(map -> {
            log("map:");
            map.forEach((key, value) -> {
                log(key + " = " + value + ";");
            });
        });
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<String> result = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("white") && colorBox instanceof StandardColorBox)
                .map(whiteBox -> (StandardColorBox)whiteBox)
                .map(whiteBox -> whiteBox.getUpperSpace().getContent())
                .filter(whiteBoxSpace -> whiteBoxSpace instanceof YourPrivateRoom.SecretBox)
                .map(whiteBoxSpace -> ((YourPrivateRoom.SecretBox) whiteBoxSpace).getText())
                .collect(Collectors.toList());
        log(result);
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
//        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
//
//        List<String> result = colorBoxList.stream()
//                .filter(colorBox -> colorBox.getColor().getColorName().equals("white") && colorBox instanceof StandardColorBox)
//                .flatMap(whiteBox -> whiteBox.getSpaceList().stream())
//                .filter(boxSpace -> boxSpace.
//        log(result);
    }
}
