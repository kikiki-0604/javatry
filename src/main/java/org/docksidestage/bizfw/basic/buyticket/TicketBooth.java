///*
// * Copyright 2019-2020 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
// * either express or implied. See the License for the specific language
// * governing permissions and limitations under the License.
// */
//package org.docksidestage.bizfw.basic.buyticket;
//
///**
// * @author jflute
// */
//public class TicketBooth {
//
//    // ===================================================================================
//    //                                                                          Definition
//    //                                                                          ==========
//    private static final int ONE_DAY_MAX_QUANTITY = 10;
//    private static final int TWO_DAY_MAX_QUANTITY = 10;
//    private static final int ONE_DAY_PRICE = 7400;
//    private static final int TWO_DAY_PRICE = 13200;
//
//    // ===================================================================================
//    //                                                                           Attribute
//    //                                                                           =========
//    private int oneDayPassQuantity = ONE_DAY_MAX_QUANTITY;
//    private int twoDayPassQuantity = TWO_DAY_MAX_QUANTITY;
//
//    private Integer salesProceeds;
//
//    private int change;
//
//    // ===================================================================================
//    //                                                                         Constructor
//    //                                                                         ===========
//    public TicketBooth() {
//    }
//
//    // ===================================================================================
//    //                                                                          Buy Ticket
//    //                                                                          ==========
//    public void buyOneDayPassport(int handedMoney) {
//        if (oneDayPassQuantity <= 0) {
//            throw new TicketSoldOutException("Sold out");
//        }
//
//        if (handedMoney < ONE_DAY_PRICE) {
//            throw new TicketShortMoneyException("Short money: " + handedMoney);
//        }
//
//        --oneDayPassQuantity;
//
//        if (salesProceeds != null) {
//            salesProceeds = salesProceeds + ONE_DAY_PRICE;
//        } else {
//            salesProceeds = ONE_DAY_PRICE;
//        }
//    }
//
//    //TwoDayPassport購入ロジック
//    public void buyTwoDayPassport(int handedMoney) {
//        if (twoDayPassQuantity <= 0) {
//            throw new TicketSoldOutException("Sold out");
//        }
//
//        if (handedMoney < TWO_DAY_PRICE) {
//            throw new TicketShortMoneyException("Short money: " + handedMoney);
//        } else if(handedMoney > TWO_DAY_PRICE){
//            change = handedMoney - TWO_DAY_PRICE;
//        }
//
//        --twoDayPassQuantity;
//
//        if (salesProceeds != null) {
//            salesProceeds = salesProceeds + TWO_DAY_PRICE;
//        } else {
//            salesProceeds = TWO_DAY_PRICE;
//        }
//    }
//
//    private void reduceQuantity(int quantity) {
//        if
//    }
//
//
//    public static class TicketSoldOutException extends RuntimeException {
//
//        private static final long serialVersionUID = 1L;
//
//        public TicketSoldOutException(String msg) {
//            super(msg);
//        }
//    }
//
//    public static class TicketShortMoneyException extends RuntimeException {
//
//        private static final long serialVersionUID = 1L;
//
//        public TicketShortMoneyException(String msg) {
//            super(msg);
//        }
//    }
//
//    // ===================================================================================
//    //                                                                            Accessor
//    //                                                                            ========
//    public int getOneDayPassQuantity() {
//        return oneDayPassQuantity;
//    }
//
//    public Integer getSalesProceeds() {
//        return salesProceeds;
//    }
//
//    public int getChange() {
//        return change;
//    }
//}
