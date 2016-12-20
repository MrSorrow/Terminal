package com.ping.terminal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.ToggleButton;


/**
 * 按钮指令界面。
 * Created by Mr.sorrow on 2016/10/12.
 */
public class SendCharOrder extends Activity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private ToggleButton[] toggleButtons = {null, null, null, null, null, null, null, null, null, null};

    private boolean[] isContent = {false, false, false, false};
    private View button1, button2, button3, button4;
    private String[] name = {"", "", "", ""};
    private String[] content = {"", "", "", ""};
    private Button[] buttons = {null, null, null, null};
    private PopOptionUtil mPop1, mPop2, mPop3, mPop4;
    private EditText et_Name1, et_Content1;

    private boolean[] isCusContent = {false, false, false, false, false, false, false, false, false, false};
    private boolean[] strOrHex = {true, true, true, true, true, true, true, true, true, true};
    private View cusButton1, cusButton2, cusButton3, cusButton4, cusButton5, cusButton6, cusButton7, cusButton8, cusButton9, cusButton10;
    private Button[] cusButtons = {null, null, null, null, null, null, null, null, null, null};
    private String[] cusContent = {"", "", "", "", "", "", "", "", "", ""};
    private String[] cusName = {"", "", "", "", "", "", "", "", "", ""};
    private PopOptionUtil cusPop1, cusPop2, cusPop3, cusPop4, cusPop5, cusPop6, cusPop7, cusPop8, cusPop9, cusPop10;
    private RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);
        toggleButtons[0] = (ToggleButton) findViewById(R.id.tb_1);
        toggleButtons[1] = (ToggleButton) findViewById(R.id.tb_2);
        toggleButtons[2] = (ToggleButton) findViewById(R.id.tb_3);
        toggleButtons[3] = (ToggleButton) findViewById(R.id.tb_4);
        toggleButtons[4] = (ToggleButton) findViewById(R.id.tb_5);
        toggleButtons[5] = (ToggleButton) findViewById(R.id.tb_6);
        toggleButtons[6] = (ToggleButton) findViewById(R.id.tb_7);
        toggleButtons[7] = (ToggleButton) findViewById(R.id.tb_8);
        toggleButtons[8] = (ToggleButton) findViewById(R.id.tb_9);
        toggleButtons[9] = (ToggleButton) findViewById(R.id.tb_10);


        //--------------------button1的功能设置----------------------
        button1 = findViewById(R.id.bt_sendCharOrder1);
        buttons[0] = (Button) button1;
        mPop1 = new PopOptionUtil(this);
        mPop1.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                mPop1.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.charbutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name[0] = et_Name1.getText().toString();
                                content[0] = et_Content1.getText().toString();
                                buttons[0].setText(name[0]);
                                if(!content[0].equals(""))
                                    isContent[0] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                mPop1.close();
                buttons[0].setText("按钮1");
                content[0] = "";
                isContent[0] = false;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContent[0]) {
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    bluetoothManager.sendMessage(content[0]);
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPop1.show(v);
                button1.setSelected(true);
                return true;
            }
        });


        //--------------------button2的功能设置----------------------
        button2 = findViewById(R.id.bt_sendCharOrder2);
        buttons[1] = (Button) button2;
        mPop2 = new PopOptionUtil(this);
        mPop2.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                mPop2.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.charbutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name[1] = et_Name1.getText().toString();
                                content[1] = et_Content1.getText().toString();
                                buttons[1].setText(name[1]);
                                if(!content[1].equals(""))
                                    isContent[1] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                mPop2.close();
                buttons[1].setText("按钮2");
                content[1] = "";
                isContent[1] = false;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContent[1]) {
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    bluetoothManager.sendMessage(content[1]);
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPop2.show(v);
                button2.setSelected(true);
                return true;
            }
        });


        //--------------------button3的功能设置----------------------
        button3 = findViewById(R.id.bt_sendCharOrder3);
        buttons[2] = (Button) button3;
        mPop3 = new PopOptionUtil(this);
        mPop3.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                mPop3.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.charbutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name[2] = et_Name1.getText().toString();
                                content[2] = et_Content1.getText().toString();
                                buttons[2].setText(name[2]);
                                if(!content[2].equals(""))
                                    isContent[2] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                mPop3.close();
                buttons[2].setText("按钮3");
                content[2] = "";
                isContent[2] = false;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContent[2]) {
                    byte[] bytes = content[2].getBytes();
                    byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    bluetoothManager.sendByte(content);
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPop3.show(v);
                button3.setSelected(true);
                return true;
            }
        });

        //--------------------button4的功能设置----------------------
        button4 = findViewById(R.id.bt_sendCharOrder4);
        buttons[3] = (Button) button4;
        mPop4 = new PopOptionUtil(this);
        mPop4.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                mPop4.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.charbutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name[3] = et_Name1.getText().toString();
                                content[3] = et_Content1.getText().toString();
                                buttons[3].setText(name[3]);
                                if(!content[3].equals(""))
                                    isContent[3] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                mPop4.close();
                buttons[3].setText("按钮4");
                content[3] = "";
                isContent[3] = false;
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContent[3]) {
                    byte[] bytes = content[3].getBytes();
                    byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    bluetoothManager.sendByte(content);
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPop4.show(v);
                button4.setSelected(true);
                return true;
            }
        });

        //--------------------cusButton1的功能设置----------------------
        cusButton1 = findViewById(R.id.bt_custom1);
        cusButtons[0] = (Button) cusButton1;
        cusPop1 = new PopOptionUtil(this);
        cusPop1.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop1.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[0] = true;
                                else
                                    strOrHex[0] = false;
                                cusName[0] = et_Name1.getText().toString();
                                cusContent[0] = et_Content1.getText().toString();
                                cusButtons[0].setText(cusName[0]);
                                isCusContent[0] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop1.close();
                cusButtons[0].setText("自定义按钮1");
                cusContent[0] = "";
                isCusContent[0] = false;
            }
        });

        cusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[0]) {
                    if (strOrHex[0]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[0]);
                    } else {
                        byte[] bytes = cusContent[0].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop1.show(v);
                cusButton1.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton2的功能设置----------------------
        cusButton2 = findViewById(R.id.bt_custom2);
        cusButtons[1] = (Button) cusButton2;
        cusPop2 = new PopOptionUtil(this);
        cusPop2.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop2.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[1] = true;
                                else
                                    strOrHex[1] = false;
                                cusName[1] = et_Name1.getText().toString();
                                cusContent[1] = et_Content1.getText().toString();
                                cusButtons[1].setText(cusName[1]);
                                if(!cusContent[1].equals(""))
                                    isCusContent[1] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop2.close();
                cusButtons[1].setText("自定义按钮2");
                cusContent[1] = "";
                isCusContent[1] = false;
            }
        });

        cusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[1]) {
                    if (strOrHex[1]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[1]);
                    } else {
                        byte[] bytes = cusContent[1].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop2.show(v);
                cusButton2.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton3的功能设置----------------------
        cusButton3 = findViewById(R.id.bt_custom3);
        cusButtons[2] = (Button) cusButton3;
        cusPop3 = new PopOptionUtil(this);
        cusPop3.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop3.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[2] = true;
                                else
                                    strOrHex[2] = false;
                                cusName[2] = et_Name1.getText().toString();
                                cusContent[2] = et_Content1.getText().toString();
                                cusButtons[2].setText(cusName[2]);
                                if(!cusContent[2].equals(""))
                                    isCusContent[2] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop3.close();
                cusButtons[2].setText("自定义按钮3");
                cusContent[2] = "";
                isCusContent[2] = false;
            }
        });

        cusButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[2]) {
                    if (strOrHex[2]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[2]);
                    } else {
                        byte[] bytes = cusContent[2].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop3.show(v);
                cusButton3.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton4的功能设置----------------------
        cusButton4 = findViewById(R.id.bt_custom4);
        cusButtons[3] = (Button) cusButton4;
        cusPop4 = new PopOptionUtil(this);
        cusPop4.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop4.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[3] = true;
                                else
                                    strOrHex[3] = false;
                                cusName[3] = et_Name1.getText().toString();
                                cusContent[3] = et_Content1.getText().toString();
                                cusButtons[3].setText(cusName[3]);
                                if(!cusContent[3].equals(""))
                                    isCusContent[3] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop4.close();
                cusButtons[3].setText("自定义按钮4");
                cusContent[3] = "";
                isCusContent[3] = false;
            }
        });

        cusButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[3]) {
                    if (strOrHex[3]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[3]);
                    } else {
                        byte[] bytes = cusContent[3].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop4.show(v);
                cusButton4.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton5的功能设置----------------------
        cusButton5 = findViewById(R.id.bt_custom5);
        cusButtons[4] = (Button) cusButton5;
        cusPop5 = new PopOptionUtil(this);
        cusPop5.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop5.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[4] = true;
                                else
                                    strOrHex[4] = false;
                                cusName[4] = et_Name1.getText().toString();
                                cusContent[4] = et_Content1.getText().toString();
                                cusButtons[4].setText(cusName[4]);
                                if(!cusContent[4].equals(""))
                                    isCusContent[4] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop5.close();
                cusButtons[4].setText("自定义按钮5");
                cusContent[4] = "";
                isCusContent[4] = false;
            }
        });

        cusButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[4]) {
                    if (strOrHex[4]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[4]);
                    } else {
                        byte[] bytes = cusContent[4].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop5.show(v);
                cusButton5.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton6的功能设置----------------------
        cusButton6 = findViewById(R.id.bt_custom6);
        cusButtons[5] = (Button) cusButton6;
        cusPop6 = new PopOptionUtil(this);
        cusPop6.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop6.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[5] = true;
                                else
                                    strOrHex[5] = false;
                                cusName[5] = et_Name1.getText().toString();
                                cusContent[5] = et_Content1.getText().toString();
                                cusButtons[5].setText(cusName[5]);
                                if(!cusContent[5].equals(""))
                                    isCusContent[5] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop6.close();
                cusButtons[5].setText("自定义按钮6");
                cusContent[5] = "";
                isCusContent[5] = false;
            }
        });

        cusButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[5]) {
                    if (strOrHex[5]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[5]);
                    } else {
                        byte[] bytes = cusContent[5].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop6.show(v);
                cusButton6.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton7的功能设置----------------------
        cusButton7 = findViewById(R.id.bt_custom7);
        cusButtons[6] = (Button) cusButton7;
        cusPop7 = new PopOptionUtil(this);
        cusPop7.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop7.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[6] = true;
                                else
                                    strOrHex[6] = false;
                                cusName[6] = et_Name1.getText().toString();
                                cusContent[6] = et_Content1.getText().toString();
                                cusButtons[6].setText(cusName[6]);
                                if(!cusContent[6].equals(""))
                                    isCusContent[6] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop7.close();
                cusButtons[6].setText("自定义按钮7");
                cusContent[6] = "";
                isCusContent[6] = false;
            }
        });

        cusButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[6]) {
                    if (strOrHex[6]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[6]);
                    } else {
                        byte[] bytes = cusContent[6].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop7.show(v);
                cusButton7.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton8的功能设置----------------------
        cusButton8 = findViewById(R.id.bt_custom8);
        cusButtons[7] = (Button) cusButton8;
        cusPop8 = new PopOptionUtil(this);
        cusPop8.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop8.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[7] = true;
                                else
                                    strOrHex[7] = false;
                                cusName[7] = et_Name1.getText().toString();
                                cusContent[7] = et_Content1.getText().toString();
                                cusButtons[7].setText(cusName[7]);
                                if(!cusContent[7].equals(""))
                                    isCusContent[7] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop8.close();
                cusButtons[7].setText("自定义按钮8");
                cusContent[7] = "";
                isCusContent[7] = false;
            }
        });

        cusButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[7]) {
                    if (strOrHex[7]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[7]);
                    } else {
                        byte[] bytes = cusContent[7].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop8.show(v);
                cusButton8.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton9的功能设置----------------------
        cusButton9 = findViewById(R.id.bt_custom9);
        cusButtons[8] = (Button) cusButton9;
        cusPop9 = new PopOptionUtil(this);
        cusPop9.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop9.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[8] = true;
                                else
                                    strOrHex[8] = false;
                                cusName[8] = et_Name1.getText().toString();
                                cusContent[8] = et_Content1.getText().toString();
                                cusButtons[8].setText(cusName[8]);
                                if(!cusContent[8].equals(""))
                                    isCusContent[8] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop9.close();
                cusButtons[8].setText("自定义按钮9");
                cusContent[8] = "";
                isCusContent[8] = false;
            }
        });

        cusButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[8]) {
                    if (strOrHex[8]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[8]);
                    } else {
                        byte[] bytes = cusContent[8].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop9.show(v);
                cusButton9.setSelected(true);
                return true;
            }
        });


        //--------------------cusButton10的功能设置----------------------
        cusButton10 = findViewById(R.id.bt_custom10);
        cusButtons[9] = (Button) cusButton10;
        cusPop10 = new PopOptionUtil(this);
        cusPop10.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                cusPop10.close();

                TableLayout setting = (TableLayout) getLayoutInflater().inflate(R.layout.custombutton_setting, null);
                et_Name1 = (EditText) setting.findViewById(R.id.et_btName);
                et_Content1 = (EditText) setting.findViewById(R.id.et_btContent);
                radioGroup1 = (RadioGroup) setting.findViewById(R.id.rg_styleChoose);
                new AlertDialog.Builder(SendCharOrder.this)
                        .setTitle("功能设置")
                        .setView(setting)
                        .setPositiveButton("绑定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (radioGroup1.getCheckedRadioButtonId() == R.id.style_string)
                                    strOrHex[9] = true;
                                else
                                    strOrHex[9] = false;
                                cusName[9] = et_Name1.getText().toString();
                                cusContent[9] = et_Content1.getText().toString();
                                cusButtons[9].setText(cusName[9]);
                                if(!cusContent[9].equals(""))
                                    isCusContent[9] = true;
                                Toast.makeText(SendCharOrder.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消绑定，不做事情
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onNextClick() {
                cusPop10.close();
                cusButtons[9].setText("自定义按钮10");
                cusContent[9] = "";
                isCusContent[9] = false;
            }
        });

        cusButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCusContent[9]) {
                    if (strOrHex[9]) {
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendMessage(cusContent[9]);
                    } else {
                        byte[] bytes = cusContent[9].getBytes();
                        byte content = (byte) (MessageUtil.charToInt(bytes[0]) * 16 + MessageUtil.charToInt(bytes[1]));
                        BluetoothManager bluetoothManager = BluetoothManager.getManager();
                        bluetoothManager.sendByte(content);
                    }
                } else {
                    Toast.makeText(SendCharOrder.this, "未给按钮设置功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cusButton10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cusPop10.show(v);
                cusButton10.setSelected(true);
                return true;
            }
        });


    }

    public void bt_openSetting(View view) {
        preferences = getSharedPreferences("myPreference", MODE_PRIVATE);
        for (int i = 0; i < 4; i++) {
            name[i] = preferences.getString("Button" + i + "_Name", "按钮" + i);
            content[i] = preferences.getString("Button" + i + "_Content", "");
            isContent[i] = preferences.getBoolean("Button" + i + "_isContent", false);
            if (isContent[i]) {
                mHandler.sendEmptyMessage(i);
            }
        }
        for(int i = 0; i < 10; i++) {
            cusName[i] = preferences.getString("cusButton" + i + "_Name", "按钮" + i);
            cusContent[i] = preferences.getString("cusButton" + i + "_Content", "");
            strOrHex[i] = preferences.getBoolean("cusButton" + i + "_StrOrHex", false);
            isCusContent[i] = preferences.getBoolean("cusButton" + i + "_isContent", false);
            if (isCusContent[i]) {
                mHandler.sendEmptyMessage(i + 4);
            }
        }
        Toast.makeText(SendCharOrder.this, "导入成功！", Toast.LENGTH_SHORT).show();
    }

    public void bt_saveSetting(View view) {
        preferences = getSharedPreferences("myPreference", MODE_PRIVATE);
        editor = preferences.edit();
        for (int i = 0; i < 4; i++) {
            editor.putString("Button" + i + "_Name", name[i]);
            editor.putString("Button" + i + "_Content", content[i]);
            editor.putBoolean("Button" + i + "_isContent", isContent[i]);
        }

        for (int i = 0; i < 10; i++) {
            editor.putString("cusButton" + i + "_Name", cusName[i]);
            editor.putString("cusButton" + i + "_Content", cusContent[i]);
            editor.putBoolean("cusButton" + i + "_StrOrHex", strOrHex[i]);
            editor.putBoolean("cusButton" + i + "_isContent", isCusContent[i]);
        }
        editor.commit();
        Toast.makeText(SendCharOrder.this, "保存成功！", Toast.LENGTH_SHORT).show();
    }

    public void tb1On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom1);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[0] = "";
            isCusContent[0] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb2On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom2);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[1] = "";
            isCusContent[1] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb3On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom3);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[2] = "";
            isCusContent[2] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb4On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom4);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[3] = "";
            isCusContent[3] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb5On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom5);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[4] = "";
            isCusContent[4] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb6On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom6);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[5] = "";
            isCusContent[5] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb7On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom7);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[6] = "";
            isCusContent[6] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb8On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom8);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[7] = "";
            isCusContent[7] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb9On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom9);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[8] = "";
            isCusContent[8] = false;
            bt.setVisibility(View.GONE);
        }
    }

    public void tb10On(View view) {
        Button bt = (Button) findViewById(R.id.bt_custom10);
        ToggleButton tb = (ToggleButton) view;
        if (tb.isChecked()) {
            bt.setVisibility(View.VISIBLE);
        } else {
            cusContent[9] = "";
            isCusContent[9] = false;
            bt.setVisibility(View.GONE);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0:
                    buttons[0].setText(name[0]);
                    break;
                case 1:
                    buttons[1].setText(name[1]);
                    break;
                case 2:
                    buttons[2].setText(name[2]);
                    break;
                case 3:
                    buttons[3].setText(name[3]);
                    break;
                case 4:
                    cusButtons[0].setText(cusName[0]);
                    if (isCusContent[0]) {
                        cusButtons[0].setVisibility(View.VISIBLE);
                        toggleButtons[0].setChecked(true);
                    } else {
                        cusButtons[0].setVisibility(View.GONE);
                        toggleButtons[0].setChecked(false);
                    }
                    break;
                case 5:
                    cusButtons[1].setText(cusName[1]);
                    if (isCusContent[1]) {
                        cusButtons[1].setVisibility(View.VISIBLE);
                        toggleButtons[1].setChecked(true);
                    } else {
                        cusButtons[1].setVisibility(View.GONE);
                        toggleButtons[1].setChecked(false);
                    }
                    break;
                case 6:
                    cusButtons[2].setText(cusName[2]);
                    if (isCusContent[2]) {
                        cusButtons[2].setVisibility(View.VISIBLE);
                        toggleButtons[2].setChecked(true);
                    } else {
                        cusButtons[2].setVisibility(View.GONE);
                        toggleButtons[2].setChecked(false);
                    }
                    break;
                case 7:
                    cusButtons[3].setText(cusName[3]);
                    if (isCusContent[3]) {
                        cusButtons[3].setVisibility(View.VISIBLE);
                        toggleButtons[3].setChecked(true);
                    } else {
                        cusButtons[3].setVisibility(View.GONE);
                        toggleButtons[3].setChecked(false);
                    }
                    break;
                case 8:
                    cusButtons[4].setText(cusName[4]);
                    if (isCusContent[4]) {
                        cusButtons[4].setVisibility(View.VISIBLE);
                        toggleButtons[4].setChecked(true);
                    } else {
                        cusButtons[4].setVisibility(View.GONE);
                        toggleButtons[4].setChecked(false);
                    }
                    break;
                case 9:
                    cusButtons[5].setText(cusName[5]);
                    if (isCusContent[5]) {
                        cusButtons[5].setVisibility(View.VISIBLE);
                        toggleButtons[5].setChecked(true);
                    } else {
                        cusButtons[5].setVisibility(View.GONE);
                        toggleButtons[5].setChecked(false);
                    }
                    break;
                case 10:
                    cusButtons[6].setText(cusName[6]);
                    if (isCusContent[6]) {
                        cusButtons[6].setVisibility(View.VISIBLE);
                        toggleButtons[6].setChecked(true);
                    } else {
                        cusButtons[6].setVisibility(View.GONE);
                        toggleButtons[6].setChecked(false);
                    }
                    break;
                case 11:
                    cusButtons[7].setText(cusName[7]);
                    if (isCusContent[7]) {
                        cusButtons[7].setVisibility(View.VISIBLE);
                        toggleButtons[7].setChecked(true);
                    } else {
                        cusButtons[7].setVisibility(View.GONE);
                        toggleButtons[7].setChecked(false);
                    }
                    break;
                case 12:
                    cusButtons[8].setText(cusName[8]);
                    if (isCusContent[8]) {
                        cusButtons[8].setVisibility(View.VISIBLE);
                        toggleButtons[8].setChecked(true);
                    } else {
                        cusButtons[8].setVisibility(View.GONE);
                        toggleButtons[8].setChecked(false);
                    }
                    break;
                case 13:
                    cusButtons[9].setText(cusName[9]);
                    if (isCusContent[9]) {
                        cusButtons[9].setVisibility(View.VISIBLE);
                        toggleButtons[9].setChecked(true);
                    } else {
                        cusButtons[9].setVisibility(View.GONE);
                        toggleButtons[9].setChecked(false);
                    }
                    break;
                default:
                    break;
            }
        }

    };

}
