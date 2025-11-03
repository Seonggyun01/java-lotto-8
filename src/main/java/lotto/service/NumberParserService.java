package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.util.Parser;
import lotto.validator.NumValidate;

/**
 * 컨트롤러에서 받은 당첨 번호 분리 및 검증
 */
public class NumberParserService {
    public List<Integer> parseNumbers(String rawNumbers){
        List<String> tokens = Parser.parse(rawNumbers);
        List<Integer> result = new ArrayList<>();

        //당첨 금액 검증 및 생성
        for(String token : tokens){
            int validateNum = NumValidate.validate(token);
            result.add(validateNum);
        }
        return result;
    }

    public int parseSingleNumber(String rawNumber){
        return NumValidate.validate(rawNumber);
    }
}
