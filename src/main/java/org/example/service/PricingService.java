package org.example.service;

import org.example.exceptions.InvalidQuantityException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PricingService {
    private static final String FREE_CUP_THRESHOLD = "n";
    private static final String DELIVERY_COST = "m";
    private static final String FREE_DELIVERY_THRESHOLD = "x";

    private ConfigurationService configurationService;

    public PricingService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void validateQuantities(List<String> quantities) throws InvalidQuantityException {
        for (String quantity : quantities) {
            if (quantity == null || quantity.isEmpty()) {
                continue;
            }
            try {
                int quantityValue = Integer.parseInt(quantity);
                if (quantityValue <= 0) {
                    throw new InvalidQuantityException("Enter a positive quantity");
                }
            } catch (NumberFormatException e) {
                throw new InvalidQuantityException("Enter a quantity");
            }
        }
    }

    public void fillOrderLists(List<String> selectedCoffeeTypes, List<String> prices, List<String> quantities,
                               List<String> coffeeTypes, List<Double> pricesList, List<Integer> quantitiesList, List<Double> sumOfEach) {

        IntStream.range(0, selectedCoffeeTypes.size()).forEach(i -> {
            String coffeeType = selectedCoffeeTypes.get(i);
            if (coffeeType.isEmpty()) {
                return;
            }
            double price = Double.parseDouble(prices.get(i));
            int quantity = Integer.parseInt(quantities.get(i));
            double sum = price * quantity;

            coffeeTypes.add(coffeeType);
            pricesList.add(price);
            quantitiesList.add(quantity);
            sumOfEach.add(sum);
        });
    }


    public double calculateTotalPrice(List<String> coffeeTypes, List<Integer> quantities,  List<Double> prices) {
        int freeCupThreshold = configurationService.getConfigurationById(FREE_CUP_THRESHOLD).getValue();

        double totalPrice = IntStream.range(0, coffeeTypes.size())
                .mapToDouble(i -> {
                    int quantity = quantities.get(i);
                    double price = prices.get(i);

                    int paidQuantity = quantity - (quantity / freeCupThreshold);

                    return price * paidQuantity;
                })
                .sum();

        return totalPrice;
    }

    public double getDeliveryCostByTotalPrice(double totalPrice) {
        double deliveryCost = configurationService.getConfigurationById(DELIVERY_COST).getValue();
        double freeDeliveryThreshold = configurationService.getConfigurationById(FREE_DELIVERY_THRESHOLD).getValue();

        if (totalPrice >= freeDeliveryThreshold) {
            return 0.0;
        }

        return deliveryCost;
    }

    public List<Integer> calculateFreeCups(List<String> coffeeTypes, List<Integer> quantities) {
        int freeCupThreshold = configurationService.getConfigurationById(FREE_CUP_THRESHOLD).getValue();

        return quantities.stream()
                .map(quantity -> quantity / freeCupThreshold)
                .collect(Collectors.toList());
    }
}
