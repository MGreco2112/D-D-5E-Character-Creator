package com.company;


import com.company.model.*;

import java.lang.Character;
import java.util.Scanner;

public class Main {
    private static Scanner gameInput = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game(gameInput);

        game.start();
    }
}
