import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

//Матрицы поворта
class Matrix4 {
    double[] values;

    Matrix4(double[] values) {
        this.values = values;
    }

    Matrix4 multiply(Matrix4 other) {
        double[] result = new double[16];//аналог матрицы в одномерном массиве
        for (int row = 0; row < 4; row++) { //счет строк
            for (int col = 0; col < 4; col++) { //счет столбцов
                for (int i = 0; i < 4; i++) { //счетчик для подсчета результата (см. статью/учебник)
                    result[row * 4 + col] += this.values[row * 4 + i] * other.values[i * 4 + col]; //подсчет конечной матрицы
                }
            }
        }
        return new Matrix4(result); // возврщаем полученную матрицу
    }

    Vertex transform(Vertex in) {  //подсчет нового вектора на основе матриц, которые считаются предыдущим методом
        return new Vertex(in.x * values[0] + in.y * values[4] + in.z * values[8] + in.w * values[12],
                in.x * values[1] + in.y * values[5] + in.z * values[9] + in.w * values[13],
                in.x * values[2] + in.y * values[6] + in.z * values[10] + in.w * values[14],
                in.x * values[3] + in.y * values[7] + in.z * values[11] + in.w * values[15]);
    }
}