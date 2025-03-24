package com.sbertech;

public class Multiple {

    public int exec(int a, int b) {
        try {
            return mult(a, b);
        }
        catch (EvenException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        finally {
            System.out.println("Вы молодец");
        }

    }

    public static int mult(int a, int b) throws EvenException {
        int result = a * b;
        if (result % 2 != 0) {
            throw new EvenException("Число нечетное");
        }
        return result;
    }
}
