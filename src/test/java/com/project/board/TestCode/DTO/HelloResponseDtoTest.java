package com.project.board.TestCode.DTO;

import com.project.board.DTO.HelloResponseDTO;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void HelloDTO_lombok(){
        String name ="test";
        int amount = 1000;
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
