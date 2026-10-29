package com.ferreiracurso.admin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static List<Long> converterStringtoList(String response) {
        return Arrays.stream(response.replaceAll("[\\[\\]\\s]", "").split(","))
                .map(Long::parseLong)
                .toList();
    }

    public static boolean compareListElements(List<Long> response, List<Long> responseCorrect) {

        List<Long> copyResponse = new ArrayList<>(response);
        List<Long> copyCorrect = new ArrayList<>(responseCorrect);

        Collections.sort(copyResponse);
        Collections.sort(copyCorrect);

        return copyResponse.equals(copyCorrect);
    }
}
