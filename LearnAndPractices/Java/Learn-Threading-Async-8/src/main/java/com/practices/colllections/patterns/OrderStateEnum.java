package com.practices.colllections.patterns;

public enum OrderStateEnum implements OrderState {
    UNPAY {
        @Override
        public void nextState(Order order) {
            order.setState(PAIED);
        }
    },
    PAIED {
        @Override
        public void nextState(Order order) {
            order.setState(FINISHED);
        }
    },
    FINISHED {
        @Override
        public void nextState(Order order) {
            // order.setState(FINISHED);
        }
    }

}
