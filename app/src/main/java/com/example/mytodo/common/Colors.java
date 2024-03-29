package com.example.mytodo.common;

import java.util.Random;

public class Colors {
    private final int[] idColors = {
                0xFFE91E63, 0xFF9C27B0, 0xFFF44336, 0xFF673AB7, 0xFF3F51B5, 0xFF2196F3, 0xFF03A9F4,
                0xFF00BCD4, 0xFF009688, 0xFF4CAF50, 0xFF8BC34A, 0xFFCDDC39, 0xFFFFEB3B, 0xFFFFC107,
                0xFFFF9800, 0xFFFF5722
        };
    private final Random random = new Random();

    public int getRandomColor(){
        return idColors[random.nextInt(idColors.length)];
    }

}
