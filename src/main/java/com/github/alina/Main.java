package com.github.alina;

import com.github.alina.ui.Cli;

public class Main {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.init();
        cli.menu();
    }
}