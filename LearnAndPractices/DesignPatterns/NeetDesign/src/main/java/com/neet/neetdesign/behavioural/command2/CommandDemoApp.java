package com.neet.neetdesign.behavioural.command2;

public class CommandDemoApp {
    public static void main(String[] args) {
        var wizard = new Wizard();
        var goblin = new Goblin();

        goblin.printStatus();

        wizard.castSpell(goblin::changeSize);
        goblin.printStatus();

        wizard.castSpell(goblin::changeVisibility);
        goblin.printStatus();

        wizard.undoLastSpell();
        goblin.printStatus();

        wizard.undoLastSpell();
        goblin.printStatus();

        wizard.redoLastSpell();
        goblin.printStatus();

        wizard.redoLastSpell();
        goblin.printStatus();
    }
}
