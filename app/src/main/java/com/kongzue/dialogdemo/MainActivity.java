package com.kongzue.dialogdemo;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.dialog.interfaces.OnBackClickListener;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnDismissListener;
import com.kongzue.dialog.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.interfaces.OnNotificationClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.util.InputInfo;
import com.kongzue.dialog.util.TextInfo;
import com.kongzue.dialog.v3.CustomDialog;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.InputDialog;
import com.kongzue.dialog.v3.Notification;
import com.kongzue.dialog.v3.ShareDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.kongzue.dialog.v3.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

@DarkStatusBarTheme(true)
@DarkNavigationBarTheme(true)
@NavigationBarBackgroundColor(a = 255, r = 236, g = 239, b = 241)
@Layout(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    
    private SmartRefreshLayout refreshLayout;
    private LinearLayout boxBody;
    private RadioGroup grpStyle;
    private RadioButton rdoIos;
    private RadioButton rdoMaterial;
    private RadioButton rdoKongzue;
    private RadioButton rdoMiui;
    private RadioGroup grpTheme;
    private RadioButton rdoLight;
    private RadioButton rdoDark;
    private TextView btnMessageDialog;
    private TextView btnSelectDialog;
    private TextView btnInputDialog;
    private TextView btnWaitDialog;
    private TextView btnTipDialog;
    private TextView btnModalDialog;
    private TextView btnShowBreak;
    private TextView btnNotify;
    private TextView btnBottomMenu;
    private TextView btnBottomMenuWithTitle;
    private TextView btnShareDialog;
    private TextView btnCustomMessageDialog;
    private TextView btnCustomInputDialog;
    private TextView btnCustomBottomMenu;
    private TextView btnCustomNotification;
    private TextView btnCustomDialog;
    private TextView btnFullScreenDialogWebPage;
    private TextView btnFullScreenDialogLogin;
    private RelativeLayout boxTable;
    private LinearLayout boxTableChild;
    private LinearLayout btnBack;
    private ImageView btnShare;
    
    @Override
    public void initViews() {
        refreshLayout = findViewById(R.id.refreshLayout);
        boxBody = findViewById(R.id.box_body);
        grpStyle = findViewById(R.id.grp_style);
        rdoIos = findViewById(R.id.rdo_ios);
        rdoMaterial = findViewById(R.id.rdo_material);
        rdoKongzue = findViewById(R.id.rdo_kongzue);
        rdoMiui = findViewById(R.id.rdo_miui);
        grpTheme = findViewById(R.id.grp_theme);
        rdoLight = findViewById(R.id.rdo_light);
        rdoDark = findViewById(R.id.rdo_dark);
        btnMessageDialog = findViewById(R.id.btn_messageDialog);
        btnSelectDialog = findViewById(R.id.btn_selectDialog);
        btnInputDialog = findViewById(R.id.btn_inputDialog);
        btnWaitDialog = findViewById(R.id.btn_waitDialog);
        btnTipDialog = findViewById(R.id.btn_tipDialog);
        btnModalDialog = findViewById(R.id.btn_modalDialog);
        btnShowBreak = findViewById(R.id.btn_showBreak);
        btnNotify = findViewById(R.id.btn_notify);
        btnBottomMenu = findViewById(R.id.btn_bottom_menu);
        btnBottomMenuWithTitle = findViewById(R.id.btn_bottom_menu_withTitle);
        btnShareDialog = findViewById(R.id.btn_shareDialog);
        btnCustomMessageDialog = findViewById(R.id.btn_customMessageDialog);
        btnCustomInputDialog = findViewById(R.id.btn_customInputDialog);
        btnCustomBottomMenu = findViewById(R.id.btn_customBottomMenu);
        btnCustomNotification = findViewById(R.id.btn_customNotification);
        btnCustomDialog = findViewById(R.id.btn_customDialog);
        btnFullScreenDialogWebPage = findViewById(R.id.btn_fullScreenDialog_webPage);
        btnFullScreenDialogLogin = findViewById(R.id.btn_fullScreenDialog_login);
        boxTable = findViewById(R.id.box_table);
        boxTableChild = findViewById(R.id.box_table_child);
        btnBack = findViewById(R.id.btn_back);
        btnShare = findViewById(R.id.btn_share);
    }
    
    private TableLayout tableLayout;
    private ViewPager viewpager;
    
    
    
    @Override
    public void initDatas(JumpParameter parameter) {
        DialogSettings.init();
        DialogSettings.checkRenderscriptSupport(this);
        DialogSettings.DEBUGMODE = true;
        DialogSettings.isUseBlur = true;
        DialogSettings.autoShowInputKeyboard = true;
        Notification.mode = Notification.Mode.FLOATING_WINDOW;
        //DialogSettings.backgroundColor = Color.BLUE;
        //DialogSettings.titleTextInfo = new TextInfo().setFontSize(50);
        //DialogSettings.buttonPositiveTextInfo = new TextInfo().setFontColor(Color.GREEN);
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        DialogSettings.theme = DialogSettings.THEME.LIGHT;
        
        refreshLayout.setEnableLoadMore(false).setEnableRefresh(false).setEnableOverScrollDrag(true);
        boxBody.setPadding(dip2px(15), dip2px(50), dip2px(15), dip2px(20));
    }
    
    private RelativeLayout boxUserName;
    private EditText editUserName;
    private RelativeLayout boxPassword;
    private EditText editPassword;
    
    //??????????????????????????????
    OnDialogButtonClickListener nextStepListener = new OnDialogButtonClickListener() {
        @Override
        public boolean onClick(BaseDialog baseDialog, View v) {
            if (isNull(editUserName.getText().toString().trim())) {
                TipDialog.show(me, "???????????????", TipDialog.TYPE.WARNING);
                return true;
            }
            
            boxUserName.animate().x(-getDisplayWidth()).setDuration(300);
            boxPassword.setX(getDisplayWidth());
            boxPassword.setVisibility(View.VISIBLE);
            boxPassword.animate().x(0).setDuration(300);
            
            editPassword.setFocusable(true);
            editPassword.requestFocus();
            
            ((FullScreenDialog) baseDialog).setCancelButton("?????????", new OnDialogButtonClickListener() {
                @Override
                public boolean onClick(BaseDialog baseDialog, View v) {
                    boxUserName.animate().x(0).setDuration(300);
                    boxPassword.animate().x(getDisplayWidth()).setDuration(300);
                    
                    editUserName.setFocusable(true);
                    editUserName.requestFocus();
                    
                    ((FullScreenDialog) baseDialog).setCancelButton("??????", null);
                    ((FullScreenDialog) baseDialog).setOkButton("?????????", nextStepListener);
                    
                    return true;
                }
            });
            ((FullScreenDialog) baseDialog).setOkButton("??????", new OnDialogButtonClickListener() {
                @Override
                public boolean onClick(BaseDialog baseDialog, View v) {
                    if (isNull(editPassword.getText().toString().trim())) {
                        TipDialog.show(me, "???????????????", TipDialog.TYPE.WARNING);
                        return true;
                    }
                    WaitDialog.show(me, "?????????...");
                    runOnMainDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TipDialog.show(me, "????????????", TipDialog.TYPE.WARNING);
                        }
                    }, 2000);
                    return true;
                }
            });
            
            return true;
        }
    };
    
    @Override
    public void setEvents() {
        //???????????????-??????
        btnFullScreenDialogLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog
                        .show(me, R.layout.layout_full_login, new FullScreenDialog.OnBindView() {
                            @Override
                            public void onBind(FullScreenDialog dialog, View rootView) {
                                boxUserName = rootView.findViewById(R.id.box_userName);
                                editUserName = rootView.findViewById(R.id.edit_userName);
                                boxPassword = rootView.findViewById(R.id.box_password);
                                editPassword = rootView.findViewById(R.id.edit_password);
                            }
                        })
                        .setOkButton("?????????", nextStepListener)
                        .setCancelButton("??????")
                        .setTitle("??????")
                ;
            }
        });
        
        //???????????????-??????
        btnFullScreenDialogWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog.show(me, R.layout.layout_full_webview, new FullScreenDialog.OnBindView() {
                    @Override
                    public void onBind(final FullScreenDialog dialog, View rootView) {
                        WebView webView;
                        
                        webView = rootView.findViewById(R.id.webView);
                        
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        webSettings.setLoadWithOverviewMode(true);
                        webSettings.setUseWideViewPort(true);
                        webSettings.setSupportZoom(false);
                        webSettings.setAllowFileAccess(true);
                        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                        webSettings.setLoadsImagesAutomatically(true);
                        webSettings.setDefaultTextEncodingName("utf-8");
                        
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                            
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                            }
                        });
                        
                        webView.loadUrl("https://github.com/kongzue/DialogV3/");
                    }
                }).setOkButton("??????", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        return false;
                    }
                }).setTitle("??????");
            }
        });
        
        //????????????????????????
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.build(me, R.layout.layout_custom_dialog, new CustomDialog.OnBindView() {
                    @Override
                    public void onBind(final CustomDialog dialog, View v) {
                        ImageView btnOk = v.findViewById(R.id.btn_ok);
                        
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.doDismiss();
                            }
                        });
                    }
                })
//                        .setCustomLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
//                        .setAlign(CustomDialog.ALIGN.DEFAULT)
//                        .setCancelable(false)
                        .setFullScreen(true)
                        .show();
            }
        });
        
        //?????????????????????-?????????
        btnCustomMessageDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog.show(me, "??????", "?????????????????????????????????", "?????????")
                        .setCustomView(R.layout.layout_custom, new MessageDialog.OnBindView() {
                            @Override
                            public void onBind(MessageDialog dialog, View v) {
                                //??????????????????
                                v.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        toast("????????????????????????");
                                    }
                                });
                            }
                        });
            }
        });
        
        //?????????????????????-?????????
        btnCustomInputDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog.show(me, "??????", "?????????????????????????????????", "?????????")
                        .setCustomView(R.layout.layout_custom, new InputDialog.OnBindView() {
                            @Override
                            public void onBind(InputDialog dialog, View v) {
                                //??????????????????
                                v.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        toast("????????????????????????");
                                    }
                                });
                            }
                        });
//                InputDialog.build(me)
//                        .setTitle("??????")
//                        .setMessage("??????")
//                        .setOnShowListener(new OnShowListener() {
//                            @Override
//                            public void onShow(BaseDialog dialog) {
//                                InputDialog inputDialog = (InputDialog) dialog;
//                                log("EditText is Null?" + (inputDialog.getEditTextView()==null));
//                            }
//                        })
//                        .setOkButton("?????????", new OnDialogButtonClickListener() {
//                            @Override
//                            public boolean onClick(BaseDialog baseDialog, View v) {
//                                return false;
//                            }
//                        })
//                        .show();
            }
        });
        
        //?????????????????????-????????????
        btnCustomBottomMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenu.show(me, new String[]{"??????1", "??????2", "??????3"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        toast(text);
                    }
                }).setCustomView(R.layout.layout_custom, new BottomMenu.OnBindView() {
                    @Override
                    public void onBind(BottomMenu bottomMenu, View v) {
                        //??????????????????
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toast("????????????????????????");
                            }
                        });
                    }
                });
            }
        });
        
        //???????????????-??????
        btnCustomNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.show(me, "??????", "????????????", R.mipmap.ico_wechat).setCustomView(R.layout.layout_custom_notification_button, new Notification.OnBindView() {
                    @Override
                    public void onBind(final Notification notification, View v) {
                        TextView btnReply;
                        TextView dismiss;
                        
                        btnReply = v.findViewById(R.id.btn_reply);
                        dismiss = v.findViewById(R.id.dismiss);
                        
                        btnReply.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notification.dismiss();
                                
                                //????????????????????????????????????????????? Activity ???????????????????????? Notification ?????? Context ????????????????????????????????? Activity???
                                //??????????????????????????? v.getContext() ?????? Notification ?????????????????? Context???
                                //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????Activity?????????
                                //startActivity(new Intent(v.getContext(), MainActivity.class)
                                //                      .addCategory(Intent.CATEGORY_LAUNCHER)
                                //                      .setAction(Intent.ACTION_MAIN)
                                //                      .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                                //);
                                
                                InputDialog.show(me, "??????", "????????????????????????",
                                        "??????", "??????"
                                );
                            }
                        });
                        
                        dismiss.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notification.dismiss();
                            }
                        });
                    }
                });
            }
        });
        
        //????????????-??????
        btnBottomMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BottomMenu.show(me, new String[]{"??????1", "??????2", "??????3"}, new OnMenuItemClickListener() {
//                    @Override
//                    public void onClick(String text, int index) {
//                        toast(text);
//                    }
//                });
                BottomMenu.build(me)
                        .setMenuTextList(new String[]{"??????1", "??????2", "??????3"})
                        .show();
                
                //List<String> datas = new ArrayList<>();
                //datas.add("??????1");
                //datas.add("??????2");
                //datas.add("??????3");
                //
                //BaseAdapter baseAdapter = new ArrayAdapter(me, com.kongzue.dialog.R.layout.item_bottom_menu_kongzue, datas);
                //
                //BottomMenu.show(me, baseAdapter, new OnMenuItemClickListener() {
                //    @Override
                //    public void onClick(String text, int index) {
                //        toast(text);
                //    }
                //});
            }
        });
        
        //?????????????????????
        btnBottomMenuWithTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenu.show(me, new String[]{"??????1", "??????2", "??????3"}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        toast("????????????" + text);
                    }
                }).setTitle("?????????????????????");
            }
        });
        
        //??????
        btnShareDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShareDialog.Item> itemList = new ArrayList<>();
                if (DialogSettings.style == DialogSettings.STYLE.STYLE_IOS) {
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_email_ios, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_qq_ios, "QQ"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_wechat_ios, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_weibo_ios, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_memorandum_ios, "????????????????????????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_remind_ios, "????????????"));
                } else {
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_email_material, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_qq_material, "QQ"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_wechat_material, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_weibo_material, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_hangout_material, "??????"));
                    itemList.add(new ShareDialog.Item(me, R.mipmap.img_remind_material, "Keep"));
                }
                
                ShareDialog.show(me, itemList, new ShareDialog.OnItemClickListener() {
                    @Override
                    public boolean onClick(ShareDialog shareDialog, int index, ShareDialog.Item item) {
                        toast("????????????" + item.getText());
                        return false;
                    }
                });
            }
        });

        //??????
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.show(me.getApplication(), "??????", "????????????", R.mipmap.ico_wechat).setOnNotificationClickListener(new OnNotificationClickListener() {
                    @Override
                    public void onClick() {
                        MessageDialog.show(me, "??????", "???????????????");
                    }
                }).setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        log("???????????????");
                    }
                });
            }
        });

        //????????????
        btnMessageDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog.show(me, "??????", "??????????????????", "??????");
            }
        });
        
        //????????????
        btnSelectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog.show(me, "??????", "??????????????????", "???", "???", "??????")
                        .setButtonOrientation(LinearLayout.VERTICAL);
            }
        });
        
        //????????????
        btnInputDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog.build(me)
                        //.setButtonTextInfo(new TextInfo().setFontColor(Color.GREEN))
                        .setTitle("??????").setMessage("??????????????????123456???")
                        .setInputText("111111")
                        .setOkButton("??????", new OnInputDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                                if (inputStr.equals("123456")) {
                                    TipDialog.show(me, "?????????", TipDialog.TYPE.SUCCESS);
                                    return false;
                                } else {
                                    TipDialog.show(me, "????????????", TipDialog.TYPE.ERROR);
                                    return true;
                                }
                            }
                        })
                        .setCancelButton("??????")
                        .setHintText("???????????????")
                        .setInputInfo(new InputInfo()
                                        .setMAX_LENGTH(6)
                                        .setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                                //.setTextInfo(new TextInfo()
                                //        .setFontColor(Color.RED)
                                //)
                        )
                        .setCancelable(true)
                        .show();
                ;
            }
        });
        
        //?????????
        btnWaitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaitDialog.show(me, "??????").setOnBackClickListener(new OnBackClickListener() {
                    @Override
                    public boolean onBackClick() {
                        toast("???????????????");
                        return false;
                    }
                });
                WaitDialog.dismiss(3000);
            }
        });
        
        //?????????????????????????????????
        btnTipDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaitDialog.show(me, "?????????...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TipDialog.show(me, "?????????", TipDialog.TYPE.SUCCESS).setOnDismissListener(new OnDismissListener() {
                                    @Override
                                    public void onDismiss() {
                                    
                                    }
                                });
                            }
                        });
                    }
                }, 1000);
            }
        });
        
        //??????????????????????????????
        btnModalDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSettings.modalDialog = true;
                MessageDialog.build(me)
                        .setTitle("??????")
                        .setMessage("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")
                        .setOkButton("??????", new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                return false;
                            }
                        })
                        .show();
                MessageDialog.show(me, "????????????", "??????????????????????????????????????????????????????Kongzue Dialog?????????????????????????????????????????????????????????????????????", "????????????", "??????????????????")
                        .setOnCancelButtonClickListener(new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                return true;
                            }
                        });
                MessageDialog.show(me, "????????????", "?????????????????????iOS?????????Kongzue????????????????????????????????????????????????????????????????????????", "?????????", "????????????", "????????????")
                        .setButtonOrientation(LinearLayout.VERTICAL);
                InputDialog.show(me, "???????????????", "????????????????????????????????????????????????", "??????");
                MessageDialog.show(me, "??????", "????????????????????????????????????????????????????????????????????????????????????????????????", "???????????????", "??????")
                        .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                btnTipDialog.callOnClick();
                                return true;
                            }
                        }).setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        DialogSettings.modalDialog = false;
                    }
                });
            }
        });
        
        //??? WindowLeaked ????????????
        btnShowBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog.show(me, "??????", "?????????AlertDialog??????????????????????????????Activity???finish???????????????WindowLeaked??????????????????????????????Kongzue Dialog??????????????????????????????????????????????????????????????????????????????Activity??????finish?????????????????????????????????????????????", "????????????", "??????")
                        .setMessageTextInfo(new TextInfo().setGravity(Gravity.LEFT))
                        .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                WaitDialog.show(me, "???????????????...");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                jump(MainActivity.class);
                                                finish();
                                            }
                                        });
                                    }
                                }, 2000);
                                return true;
                            }
                        });
            }
        });
        
        //???????????????
        grpStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdo_ios:
                        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
                        break;
                    case R.id.rdo_kongzue:
                        DialogSettings.style = DialogSettings.STYLE.STYLE_KONGZUE;
                        break;
                    case R.id.rdo_material:
                        DialogSettings.style = DialogSettings.STYLE.STYLE_MATERIAL;
                        break;
                    case R.id.rdo_miui:
                        DialogSettings.style = DialogSettings.STYLE.STYLE_MIUI;
                        break;
                }
            }
        });
        
        //???????????????
        grpTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdo_light:
                        DialogSettings.theme = DialogSettings.THEME.LIGHT;
                        DialogSettings.tipTheme = DialogSettings.THEME.DARK;
                        break;
                    case R.id.rdo_dark:
                        DialogSettings.theme = DialogSettings.THEME.DARK;
                        DialogSettings.tipTheme = DialogSettings.THEME.LIGHT;
                        break;
                }
            }
        });
        
        //?????????Github????????????Star&Fork
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://github.com/kongzue/DialogV3");
            }
        });
        
    }
    
    public static boolean isRunningForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        // ????????????
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void toast(final Object obj) {
        Toast.makeText(getBaseContext(), obj.toString(), Toast.LENGTH_SHORT).show();
    }
}
