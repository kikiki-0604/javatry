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
package org.docksidestage.javatry.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.ScaleTransition;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() {
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7

        assertEquals(7, sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 9

        //条件に当てはまったら分岐をぬける
        assertEquals(7, sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else {
            sea = 9;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10

        assertEquals(10, sea);
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside

        assertEquals("dockside", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp

        assertEquals("magiclamp", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? =>　hangar

        //startsWith = 指定された文字列で始まるか　真　true　偽　false
        //contains = 文字列に指定の文字が含まれているか　真　true　偽　false
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside

        assertEquals("dockside", sea);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stateList = prepareStageList();
        List<String> resultList = new ArrayList<>();
        for (String includeA : stateList) {
            if (includeA.contains("a")) {
                resultList.add(includeA);
            }
        }
        log(resultList);

        for(String result : resultList){
            assertTrue(result.contains("a"));
        }

//        List<String> checkList = stateList.stream()
//                .filter(state -> state.contains("a"))
//                .collect(Collectors.toList());
//
//        log(checkList);
//
//        assertEquals(resultList,checkList);



        //TODO:assertでリストの各要素があっているかを確認する方法がある？StreamAPIでいけそう？filter?
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea = null;
        //        for (String stage : stageList) {
        //            if (stage.startsWith("br")) {
        //                continue;
        //            }
        //            sea = stage;
        //            if (stage.contains("ga")) {
        //                break;
        //            }
        //        }
        //
        //        log(sea); // should be same as before-fix

        try {
            for (String stage : stageList) {
                if (stage.startsWith("br")) {
                    continue;
                }
                sea = stage;
                if (stage.contains("ga")) {
                    throw new RuntimeException();
                }
            }
        } catch (RuntimeException e) {
        }

        log(sea); // should be same as before-fix

    }

    //forEachではcontineは使えない。エラーになる
    //breakは例外処理を利用する方法がある。コードが複雑になるので推奨されないっぽい
    //TODO：レビューしてもらう

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * ①if文とfor文を使って9×9を表示させる
     * ②リストから「way」を含む要素と「ma」から始まる要素だけを表示する
     *
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(i * j + " ");
                if (j == 9) {
                    System.out.println();
                }
            }
        }
    }

    public void test2_iffor_yourExercise() {
        List<String> stageList = prepareStageList();
        List<String> resultList = new ArrayList<>();
        stageList.forEach(stage ->{
            if(stage.endsWith("way") || stage.startsWith("ma")){
                resultList.add(stage);
            }
        });
        log(resultList);
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
