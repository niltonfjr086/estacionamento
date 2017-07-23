package br.com.linux_park.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author Nilton
 */
public class PlacaTextField extends TextField {

    public PlacaTextField() {
        this.habilitarPlacaMask(this);
    }

    private void habilitarPlacaMask(final PlacaTextField inpPlaca) {

        String t = "Testa";
        System.out.println(t);

        inpPlaca.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

//                IMPLEMENTAÇÃO PRIMÁRIA
//                if (newValue.length() > 0 && newValue.length() < 4) {
//                    int tecla = newValue.charAt(newValue.length() - 1);
//                    if ((tecla > 64 && tecla < 91) || (tecla > 96 && tecla < 123)) {
//                        inpPlaca.setText(newValue.toUpperCase());
//                    } else {
//                        inpPlaca.setText(oldValue.toUpperCase());
//                    }
//                    if (newValue.length() == 3 && oldValue.length() != 4) {
//                        inpPlaca.setText(newValue.toUpperCase() + "-");
//                    }
//                    if (newValue.length() == 3 && oldValue.length() == 4) {
//                        inpPlaca.setText(newValue.substring(0, 2).toUpperCase());
//                    }
//
//                } else if (newValue.length() > 4 && newValue.length() < 9) {
//                    if (!Character.isDigit(newValue.charAt(newValue.length() - 1))) {
//                        inpPlaca.setText(oldValue.toUpperCase());
//                    }
//                } else if (newValue.length() > 8) {
//                    inpPlaca.setText(oldValue.toUpperCase());
//                } else if (newValue.length() > 0 && newValue.length() < 9) {
//                    inpPlaca.setText(newValue.toUpperCase());
//                }
//
//
//                IMPLEMENTAÇÃO SECUNDÁRIA
                if (newValue.length() > 0) {

                    inpPlaca.setText(formatado(oldValue, newValue).toUpperCase());

                }

            }

            private String formatado(String oldValue, String newValue) {

                char[] novo = newValue.toCharArray();
                for (int i = 0; i < novo.length; i++) {

                    int tecla = novo[i];

                    if (i < 3) {
                        if (!(tecla > 64 && tecla < 91) && !(tecla > 96 && tecla < 123)) {
                            return oldValue;
                        }
                    } else {
                        if (i == 3) {
                            if (novo[i] != '-') {
                                if (oldValue.length() == 3) {
                                    if (Character.isDigit(novo[i])) {
                                        return (oldValue + "-" + novo[i]);
                                    }
                                    return (oldValue + "-");
                                }
                                if (oldValue.length() != 3 && newValue.length() < 8) {
                                    if (Character.isDigit(novo[i])) {
                                        return (newValue.substring(0,3) + "-" + novo[i] + newValue.substring(4));
                                    }
                                }
                                return (oldValue);
                            }
                        } else {
                            if (i > 3 && i < 8) {
                                if (!Character.isDigit(novo[i])) {
                                    return oldValue;
                                }
                            } else {
                                return oldValue;
                            }
                        }
                    }
                }
                return newValue;
            }
        }
        );
    }
}
