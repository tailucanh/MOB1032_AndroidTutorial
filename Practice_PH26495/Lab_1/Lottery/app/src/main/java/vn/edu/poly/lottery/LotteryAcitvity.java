package vn.edu.poly.lottery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LotteryAcitvity extends AppCompatActivity {
    Button btn_QuaySo;
    EditText ed_NumberChoise;
    TextView txt_Kq, txt_Notifaction,txt_False, txt_Bingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_acitvity);




        btn_QuaySo = findViewById(R.id.btnQuaySo);
        ed_NumberChoise = findViewById(R.id.ed_Number);
        txt_Kq = findViewById(R.id.txt_Kqua);
        txt_Notifaction = findViewById(R.id.txt_Notification);
        txt_False = findViewById(R.id.txt_False);
        txt_Bingo = findViewById(R.id.txt_Bingo);

        btn_QuaySo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(99);
                    String numberYourChoise = ed_NumberChoise.getText().toString();
                    if(numberYourChoise.isEmpty()){
                        Toast.makeText(getBaseContext(),"Hãy nhập số của bạn !",Toast.LENGTH_SHORT).show();
                    }else{
                        txt_Notifaction.setVisibility(View.GONE);
                        txt_Kq.setText("Số độc đắc : "+randomNumber);
                        if (numberYourChoise.equals(String.valueOf(randomNumber))){
                            txt_Bingo.setText("Chúc mừng bạn đã trúng thưởng !");
                            txt_Bingo.setVisibility(View.VISIBLE);
                            txt_False.setVisibility(View.GONE);
                        }else{
                            txt_Bingo.setVisibility(View.GONE);
                            txt_False.setVisibility(View.VISIBLE);
                            txt_False.setText("Chúc bạn may mắn lần sau !");
                        }
                    }


            }
        });


    }
}