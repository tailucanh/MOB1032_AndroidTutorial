package vn.edu.poly.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView tv_showInput, tv_showOutput;
    Button AC, delete, percent, cong , tru , nhan, chia, result,decimal, btn_00;
    Button number1,number2,number3,number4,number5,number6,number7,number8,number9,number0;

    ArrayList<String> arrOperation;
    ArrayList<Double> arrNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "0");
                Result();
            }
        });
        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "1");
                Result();
            }
        });
        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "2");
                Result();
            }
        });
        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "3");
                Result();
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "4");
                Result();
            }
        });
        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "5");
                Result();
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "6");
                Result();
            }
        });
        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "7");
                Result();
            }
        });
        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "8");
                Result();
            }
        });
        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showInput.setText(tv_showInput.getText() + "9");
                Result();
            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_showInput.getText().length() >0) if(Character.isDigit(tv_showInput.getText().charAt(tv_showInput.getText().length()-1)) )  tv_showInput.setText(tv_showInput.getText()+".");


            }
        });
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_showInput.getText().length() > 0){
                    if(Character.isDigit(tv_showInput.getText().charAt(tv_showInput.getText().length()-1))){
                        tv_showInput.setText(tv_showInput.getText()+" + ");
                        if(tv_showOutput.getText() != "0" && tv_showInput.getText() == ""){
                            tv_showInput.setText(tv_showOutput.getText() + " + ");
                        }
                    }
                }
            }
        });
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_showOutput.getText()!="0" && tv_showInput.getText()=="")
                {
                    tv_showInput.setText(tv_showOutput.getText()+" - ");
                }
                if(tv_showInput.getText().length()==0) tv_showInput.setText(tv_showInput.getText()+"-");

                else if(Character.isDigit(tv_showInput.getText().charAt(tv_showInput.getText().length()-1)) || tv_showInput.getText().charAt(tv_showInput.getText().length()-1)=='/' || tv_showInput.getText().charAt(tv_showInput.getText().length()-1)=='x' )
                        tv_showInput.setText(tv_showInput.getText()+" - ");

            }

        });
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_showInput.getText().length() > 0){
                    if(Character.isDigit(tv_showInput.getText().charAt(tv_showInput.getText().length()-1))){
                        tv_showInput.setText(tv_showInput.getText()+" x " );
                        if(tv_showOutput.getText() != "0" && tv_showInput.getText() == ""){
                            tv_showInput.setText(tv_showOutput.getText() + " x ");
                        }
                    }
                }
            }
        });
        chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_showInput.getText().length() >0) if(Character.isDigit(tv_showInput.getText().charAt(tv_showInput.getText().length()-1)))  tv_showInput.setText(tv_showInput.getText()+" ÷ ");
                if(tv_showOutput.getText()!="0" && tv_showInput.getText()=="")
                {
                    tv_showInput.setText(tv_showOutput.getText()+" ÷ ");
                }
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_showInput.getText().length() > 1 ) Result();
                tv_showInput.setText("");
            }
        });

    }
    public void delete(){
        StringBuffer buffer = new StringBuffer(tv_showInput.getText());
        if(buffer.length() > 0){
            buffer.deleteCharAt(buffer.length() - 1);
            tv_showInput.setText(buffer);
            if(buffer.length() > 0){
                Result();
            }else{
                tv_showOutput.setText("0");
            }
        }
    }



    public void deleteAll(){
        tv_showOutput.setText("0");
        tv_showInput.setText("");
    }

    public int addOperation(String input) {
        arrOperation = new ArrayList<>();
        addNumber(this.tv_showInput.getText().toString());
        if(this.tv_showInput.getText().charAt(0)=='-') arrNumbers.set(0,-arrNumbers.get(0));
        char[] cArray = input.toCharArray();
        for (int i = 1; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case 'x':
                    arrOperation.add(cArray[i] + "");
                    if(i<cArray.length-1) if(cArray[i+1] =='-' && i+1 <cArray.length-1 )
                    {
                        arrNumbers.set(arrOperation.size(),-arrNumbers.get(arrOperation.size()));
                        i++;
                    }
                    break;
                case '÷':
                    arrOperation.add(cArray[i] + "");
                    if(i<cArray.length-1) if(cArray[i+1] =='-' && i+1 <cArray.length-1 )
                    {
                        arrNumbers.set(arrOperation.size(),-arrNumbers.get(arrOperation.size()));
                        i++;
                    }
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String stringInput) {
        arrNumbers = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumbers.add(Double.valueOf(matcher.group(1)));
        }
    }
    public void Result()
    {
        DecimalFormat df = new DecimalFormat("###.#######");
        double result = 0;
        addOperation(tv_showInput.getText().toString());
        if(arrNumbers.size()==1)  result = arrNumbers.get(0);
        if(arrOperation.size()>arrNumbers.size() ||arrOperation.size()<0){
            tv_showOutput.setText("Input error!!");
        }else {
            for (int i = 0; i < (arrNumbers.size() - 1); i++) {
                switch (arrOperation.get(i)) {
                    case "x":
                        arrNumbers.set(i, arrNumbers.get(i) * arrNumbers.get(i + 1));
                        arrNumbers.remove(i+1); arrOperation.remove(i);
                        i--;
                        break;
                    case "÷":
                        arrNumbers.set(i, arrNumbers.get(i) / arrNumbers.get(i + 1));
                        arrNumbers.remove(i+1); arrOperation.remove(i);
                        i--;
                    default:
                        break;
                }
            }
            result = arrNumbers.get(0);
            for (int i = 0; i < (arrNumbers.size() - 1); i++) {
                switch (arrOperation.get(i)) {
                    case "+":
                        result = result + arrNumbers.get(i + 1);
                        break;
                    case "-":
                        result = result - arrNumbers.get(i + 1);
                        break;
                    default:
                        break;
                }
            }
            tv_showOutput.setText(df.format(result) + "");
        }
    }



    public void anhXa(){
        tv_showInput = findViewById(R.id.tv_show);
        tv_showOutput = findViewById(R.id.tv_kq);

        percent =findViewById(R.id.btn_Percent);
        AC = findViewById(R.id.btn_AC);
        delete = findViewById(R.id.btn_Del);
        cong = findViewById(R.id.btn_dauCong);
        tru = findViewById(R.id.btbn_dauTru);
        nhan = findViewById(R.id.btn_dauNhan);
        chia = findViewById(R.id.btn_dauChia);
        result = findViewById(R.id.btn_result);
        decimal = findViewById(R.id.btn_decimal);
        btn_00 = findViewById(R.id.btn_00);

        number0 = findViewById(R.id.btn_Number0);
        number1 = findViewById(R.id.btn_Number1);
        number2 = findViewById(R.id.btn_Number2);
        number3 = findViewById(R.id.btn_Number3);
        number4 = findViewById(R.id.btn_Number4);
        number5 = findViewById(R.id.btn_Number5);
        number6 = findViewById(R.id.btn_Number6);
        number7 = findViewById(R.id.bnt_Number7);
        number8 = findViewById(R.id.btn_Number8);
        number9 = findViewById(R.id.btn_Number9);

    }


}