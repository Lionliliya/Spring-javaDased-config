package com.kiev.java;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ExecutorImplTest {

    @Test
    public void executeWithoutValidator() throws Exception {
        ExecutorImpl<Integer> executor = new ExecutorImpl<>();
        executor.addTask(new AddTask(1,2));
        executor.execute();
        assertEquals("Wrong valid result list size", executor.getValidResults().size(), 1);
        assertEquals( "Wrong inValid result list size", executor.getInvalidResults().size(), 0);
        assertEquals( "Wrong result", executor.getValidResults().get(0), Integer.valueOf(3));
    }

    @Test
    public void executeWithValidator() throws Exception {
        ExecutorImpl<Integer> executor = new ExecutorImpl<>();
        executor.addTask(new AddTask(1,-2), result -> result >= 0);
        executor.execute();
        assertEquals("Wrong result", executor.getInvalidResults().get(0), Integer.valueOf(-1));
        assertEquals("Wrong result", executor.getInvalidResults().size(), 1);
        assertEquals( "Wrong result", executor.getValidResults().size(), 0);
    }

    @Test
    public void testExecutor() throws Exception {
        ExecutorImpl<Integer> executor = new ExecutorImpl<>();
        executor.addTask(new AddTask(1,-2));
        executor.addTask(new AddTask(1,-2), result -> result >= 0);
        executor.addTask(new AddTask(1,2), result -> result >= 0);
        executor.addTask(new AddTask(Integer.MAX_VALUE, 1), result -> result >= 0);

        executor.execute();

        assertEquals("Wrong result", executor.getInvalidResults().size(), 2);
        assertEquals( "Wrong result", executor.getValidResults().size(), 2);
        assertEquals("Wrong result", executor.getInvalidResults().get(0), Integer.valueOf(-1));
        assertEquals("Wrong result", executor.getInvalidResults().get(1), Integer.valueOf(Integer.MIN_VALUE));
        assertEquals("Wrong result", executor.getValidResults().get(0), Integer.valueOf(-1));
        assertEquals("Wrong result", executor.getValidResults().get(1), Integer.valueOf(3));

    }

    public static class AddTask implements Task<Integer> {

        private int value1;
        private int value2;
        private int result;


        public AddTask(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        @Override
        public void execute() {
            result = value1 + value2;
        }

        @Override
        public Integer getResult() {
            return result;
        }

        public int getValue2() {
            return value2;
        }

        public int getValue1() {
            return value1;
        }
    }
}