package com.example.calcilator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	private Character character;
	private Float num1 = 0.0f;
	private Float num2 = 0.0f;
	private int flag = 0;//是否计算完成
	private String numstr = "";
	private String charstr = "";
	private TextView text;
	private Button[] buttons = new Button[11];
	private Button[] buttons2 = new Button[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }


    private void initListener() {
		// TODO 自动生成的方法存根
    	for (Button num : buttons) {
			num.setOnClickListener(new NumberClickListener());
		}

		for (Button _char : buttons2) {
			_char.setOnClickListener(new BtnClickListener());
		}
	}

    private class NumberClickListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Button bt = (Button) arg0;
			if (character == null) {
				if (flag == 1) {
					numstr = "";
					flag = 0;
					numstr = bt.getText().toString();
					text.setText(numstr);
				} else {
					numstr += bt.getText().toString();
					text.setText(numstr);
					buttons2[7].setText("C");
				}
			} else {
				numstr += bt.getText().toString();
				text.setText(numstr);
				buttons2[7].setText("C");
			}

		}
	}
    
    private class BtnClickListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Button bt = (Button) arg0;
			charstr = bt.getText().toString();
			switch (charstr) {
			case "C":
				numstr = "";
				text.setText("0");
				buttons2[7].setText("AC");
				break;
			case "AC":
				numstr = "";
				text.setText("0");
				num1 = 0.0f;
				num2 = 0.0f;
				character = null;
				break;
			case "+/-":
				if (character == null) {
					if (numstr != "") {
						num1 = Float.parseFloat(numstr);
						if(num1>0){
							num1 = -num1;
							numstr = "-" + numstr;
						}else if(num1<0){
							num1 = -num1;
							numstr = numstr.replace("-", "");
						}
						text.setText(numstr);
					} else {
						break;
					}
				} else {
					if (numstr != "") {
						num2 = Float.parseFloat(numstr);
						num2 = -num2;
						numstr = "-" + numstr;
						text.setText(numstr);
					} else {
						break;
					}
				}
				break;
			case "%":
				if (character == null) {
					if (numstr != "") {
						num1 = Float.parseFloat(numstr);
						num1 = num1 / 100;
						numstr = num1 + "";
						text.setText(numstr);
					} else {
						break;
					}
				} else {
					if (numstr != "") {
						num2 = Float.parseFloat(numstr);
						num2 = num2 / 100;
						numstr = num2 + "";
						text.setText(numstr);
					} else {
						break;
					}
				}
				break;
			case "/":
				if (numstr != "") {
					num1 = Float.parseFloat(numstr);
					character = '/';
					numstr = "";
					break;
				} else {
					break;
				}
			case "X":
				if (numstr != "") {
					num1 = Float.parseFloat(numstr);
					character = 'X';
					numstr = "";
					break;
				} else {
					break;
				}
			case "+":
				if (numstr != "") {
					num1 = Float.parseFloat(numstr);
					character = '+';
					numstr = "";
					break;
				} else {
					break;
				}
			case "һ":
				if (numstr != "") {
					num1 = Float.parseFloat(numstr);
					character = 'һ';
					numstr = "";
					break;
				} else {
					break;
				}
			case "=":
				if (character == null) {
					break;
				} else {
					num2 = Float.parseFloat(numstr);
					numstr = caculate(character, num1, num2) + "";
					text.setText(numstr);
					character = null;
					flag = 1;
				}
				break;
			default:
				break;
			}

		}
	}
    
    private Float caculate(Character character, Float Float1, Float Float2) {
		if (character == 'һ') {
			return Float1 - Float2;
		} else if (character == '+') {
			return Float1 + Float2;
		} else if (character == 'X') {
			return Float1 * Float2;
		} else if (character == '/') {
			return Float1 / Float2;
		} else
			return null;

	}


	private void initView() {
		// TODO 自动生成的方法存根
    	buttons[0] = (Button) findViewById(R.id.btn_0);
		buttons[1] = (Button) findViewById(R.id.btn_1);
		buttons[2] = (Button) findViewById(R.id.btn_2);
		buttons[3] = (Button) findViewById(R.id.btn_3);
		buttons[4] = (Button) findViewById(R.id.btn_4);
		buttons[5] = (Button) findViewById(R.id.btn_5);
		buttons[6] = (Button) findViewById(R.id.btn_6);
		buttons[7] = (Button) findViewById(R.id.btn_7);
		buttons[8] = (Button) findViewById(R.id.btn_8);
		buttons[9] = (Button) findViewById(R.id.btn_9);
		buttons[10] = (Button) findViewById(R.id.btn_poin);

		buttons2[0] = (Button) findViewById(R.id.btn_divi);
		buttons2[1] = (Button) findViewById(R.id.btn_mult);
		buttons2[2] = (Button) findViewById(R.id.btn_supt);
		buttons2[3] = (Button) findViewById(R.id.btn_add);
		buttons2[4] = (Button) findViewById(R.id.btn_equa);
		buttons2[5] = (Button) findViewById(R.id.btn_per);
		buttons2[6] = (Button) findViewById(R.id.btn_posi);
		buttons2[7] = (Button) findViewById(R.id.btn_c);

		text = (TextView) findViewById(R.id.print);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
