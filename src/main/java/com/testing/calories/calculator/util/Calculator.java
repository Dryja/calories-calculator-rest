package com.testing.calories.calculator.util;

public class Calculator {
  public static Double calculateEnergyRequirements(Double bmr, String physicalActivity) {
    double energyRequirements;

    switch (physicalActivity) {
      case "sedentary": {
        energyRequirements = 1.2 * bmr;
        break;
      }
      case "lightly": {
        energyRequirements = 1.375 * bmr;
        break;
      }
      case "moderately": {
        energyRequirements = 1.55 * bmr;
        break;
      }
      case "very": {
        energyRequirements = 1.725 * bmr;
        break;
      }
      default: {
        energyRequirements = 1.9 * bmr;
        break;
      }
    }

    return energyRequirements;
  }

  public static Double calculateBMR(String sex, Integer height, Integer weight, Integer age) {
    var bmr = 10 * weight + 6.25 * height - 5 * age;
    return sex.equals("male") ? bmr + 5 : bmr - 161;
  }

  public static Integer chooseEnergyOption(String goal, Double energyRequirements) {
    Double energy = energyRequirements;

    switch (goal) {
      case "reduce": {
        energy *= 0.85;
        break;
      }
      case "gain": {
        energy *= 1.15;
        break;
      }
      default: {
        break;
      }
    }

    return (int) Math.round(energy);
  }
}
