package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {

    private List<AdditionOperation> additionOperations = new ArrayList<>();

    @GetMapping("/add")
    public String addNumbers(
            @RequestParam(name = "num1") int num1,
            @RequestParam(name = "num2") int num2) {

        if (num1 < 1 || num1 > 100 || num2 < 1 || num2 > 100) {
            return "Numbers must be between 1 and 100.";
        }

        int sum = num1 + num2;
        additionOperations.add(new AdditionOperation(num1, num2, sum));
        System.out.println(additionOperations);
        return "The sum of " + num1 + " and " + num2 + " is: " + sum;
    }

    @GetMapping("/searchAndSort")
    public String searchAndSort(
            @RequestParam(name = "numToSearch") int numToSearch,
            @RequestParam(name = "sortingAttribute") String sortingAttribute) {

        System.out.println(additionOperations);

        List<AdditionOperation> filteredOperations = additionOperations.stream()
                .filter(operation -> operation.getNum1() == numToSearch || operation.getNum2() == numToSearch)
                .collect(Collectors.toList());

        Comparator<AdditionOperation> comparator = sortingAttribute.equals("ascending")
                ? Comparator.comparing(AdditionOperation::getSum)
                : Comparator.comparing(AdditionOperation::getSum).reversed();

        List<AdditionOperation> sortedOperations = filteredOperations.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        return sortedOperations.toString();
    }

    private static class AdditionOperation {
        private final int num1;
        private final int num2;
        private final int sum;

        public AdditionOperation(int num1, int num2, int sum) {
            this.num1 = num1;
            this.num2 = num2;
            this.sum = sum;
        }

        public int getNum1() {
            return num1;
        }

        public int getNum2() {
            return num2;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return "Num1: " + num1 + ", Num2: " + num2 + ", Sum: " + sum;
        }
    }
}