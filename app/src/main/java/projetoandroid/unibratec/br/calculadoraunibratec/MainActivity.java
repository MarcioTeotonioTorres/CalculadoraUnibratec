package projetoandroid.unibratec.br.calculadoraunibratec;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.text.DecimalFormat;

public class MainActivity extends Activity
{

    TextView textView;
    TextView textOperador;
    String  stringAtual="0";
    String stringAnterior=null;
    boolean stringVeja=false;
    int operadorAtual=0;


    DecimalFormat format = new DecimalFormat("0.#");

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.vejaOperacao);
        textOperador=(TextView)findViewById(R.id.vejaOperador);

        int botoesNumericos[] = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};

        NumberButtonClickListener botaoNumericoClickListner = new NumberButtonClickListener();

        for(int id:botoesNumericos)

        {	View v = findViewById(id);
            v.setOnClickListener(botaoNumericoClickListner);
        }

        int botoesOperadores[] = {R.id.botaoPorcento,R.id.botaoMaisOuMenos,R.id.botaoSomar,R.id.botaoSubtrair,R.id.botaoDividir,R.id.botaoMultiplicar,R.id.botaoDecimal,R.id.botaoLimpar,R.id.botaoIgual};

        OpperandButtonClickListener operadorClickListner = new OpperandButtonClickListener();

        for(int id:botoesOperadores)

        {	View v = findViewById(id);
            v.setOnClickListener(operadorClickListner);

        }
        setStringAtual("0");


    }
    void setStringAtual(String s){
        stringAtual = s;
        textView.setText(s);
    }

    class NumberButtonClickListener implements OnClickListener{


        @Override public void onClick(View v) {
            if(stringVeja){
                stringAnterior=stringAtual;
                stringAtual="0";
                stringVeja=false;
            }
        String text=(String)((Button)v).getText();

            if(stringAtual.equals("0")){
            setStringAtual(text);
            }
        else {
            setStringAtual(stringAtual + text);
            }
        }
    }
    class OpperandButtonClickListener implements OnClickListener{
        @Override public void onClick(View v)
    {	int id=v.getId();


        if(id == R.id.botaoMaisOuMenos) { //aqui neste caso é preciso a ação do botao igual para inverter
          double prev = Double.parseDouble(stringAtual);

           prev = prev *(-1);
           setStringAtual(String.valueOf(format.format(prev)));
           stringVeja = true;

        }
        if(id == R.id.botaoPorcento){
            operadorAtual = id;
            textOperador.setText(stringAtual+"%");
            stringAnterior = stringAtual;
            stringVeja = true;
        }

        if(id == R.id.botaoLimpar){
            stringVeja=false;
            setStringAtual("0");
            stringAnterior=null;
            textOperador.setText("");
        }
        if(id == R.id.botaoDecimal) {

            if (stringAtual.contains(".")) {
            } else {
                setStringAtual(stringAtual + ".");
            }
        }
        if(id == R.id.botaoSomar){

            operadorAtual = id;
            textOperador.setText(stringAtual+"+");
            stringAnterior = stringAtual;
            stringVeja = true;
        }
        if(id == R.id.botaoSubtrair){

            operadorAtual = id;
            stringAnterior = stringAtual;
            textOperador.setText(stringAtual+"-");
            stringVeja = true;
        }
        if(id == R.id.botaoMultiplicar){

            operadorAtual = id;
            stringAnterior = stringAtual;
            textOperador.setText(stringAtual+"*");
            stringVeja = true;
        }
        if(id == R.id.botaoDividir){

            operadorAtual = id;
            stringAnterior = stringAtual;
            textOperador.setText(stringAtual+"/");
            stringVeja = true;
        }


      //  if(id == R.id.botaoSomar||id == R.id.botaoSubtrair||id == R.id.botaoMultiplicar||id == R.id.botaoDividir) {
         //   operadorAtual = id;
         //   stringAnterior =s tringAtual;
         //   stringVeja = true;
        //}
        if(id == R.id.botaoIgual){

            double curr=Double.parseDouble(stringAtual);
            double result=0;

            if(stringAnterior != null){

                double prev = Double.parseDouble(stringAnterior);

                switch(operadorAtual)
                {	case R.id.botaoSomar: result = prev + curr;
                        textOperador.setText("");
                        textOperador.setText(stringAnterior+" + "+stringAtual+" =");
                    break;
                    case R.id.botaoSubtrair: result = prev - curr;
                        textOperador.setText("");
                        textOperador.setText(stringAnterior+" - "+stringAtual+" =");
                    break;
                    case R.id.botaoMultiplicar: result = prev * curr;
                        textOperador.setText("");
                        textOperador.setText(stringAnterior+" * "+stringAtual+" =");
                    break;
                    case R.id.botaoDividir: result = prev/curr;
                        textOperador.setText("");
                        textOperador.setText(stringAnterior+" / "+stringAtual+" =");
                    break;
                    case R.id.botaoPorcento: result = (prev / 100 )* curr;
                        textOperador.setText("");
                        textOperador.setText(stringAnterior+" % "+stringAtual+" =");
                    break;
                   // case R.id.botaoMaisOuMenos://clique necessário para inversão de sinal
                     //   if(prev > 0) {
                       //     result = prev * (-1);
                         //   break;
                        //}
                          //  else {
                            //result = prev * 1;
                            //break;
                        //}


                }
            }
            setStringAtual(format.format(result));
        }
    }
    }
}
