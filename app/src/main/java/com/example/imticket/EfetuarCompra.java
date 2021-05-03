package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import adapter.IngressoCompraAdapter;
import adapter.PartidaAdapter;
import controller.ConexaoController;
import modelDominio.CompraIngresso;
import modelDominio.DadosCompra;
import modelDominio.Ingresso;

public class EfetuarCompra extends AppCompatActivity {
    RecyclerView rvDadosCompra;
    InformacoesApp informacoesApp;
    IngressoCompraAdapter ingressoCompraAdapter;

    LinearLayout llPrecoCompra;
    TextView tvprecototalcompra;
    Button btConfirmarCompra;
    EditText etnomeCompra, etcpfcompra, etcelularCompra, etCepCompra, etRuaComprar, etBairroComprar, etNumCasa,
            etComplementoComprar, etnumerocartao;
    Spinner SpUf, SpNomeCartao;
    RadioButton rbCredito, rbDebito;
    RadioGroup RGTipoCartao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_compra);

        Spinner SpNomeCartao, SpUf;

        SpNomeCartao = findViewById(R.id.SpNomeCartao);
        SpUf = findViewById(R.id.SpUf);


        rvDadosCompra = findViewById(R.id.rvDadosCompra);
        llPrecoCompra = findViewById(R.id.llPrecoCompra);
        tvprecototalcompra = findViewById(R.id.tvprecototalcompra);
        btConfirmarCompra = findViewById(R.id.btConfirmarCompra);
        RGTipoCartao = findViewById(R.id.RGTipoCartao);


        etnomeCompra = findViewById(R.id.etnomeCompra);
        etcpfcompra = findViewById(R.id.etcpfcompra);
        etcelularCompra = findViewById(R.id.etcelularCompra);
        etnumerocartao = findViewById(R.id.etnumerocartao);

        etCepCompra = findViewById(R.id.etCepCompra);
        etRuaComprar = findViewById(R.id.etRuaComprar);
        etBairroComprar = findViewById(R.id.etBairroComprar);
        etNumCasa = findViewById(R.id.etNumCasa);
        etComplementoComprar = findViewById(R.id.etComplementoComprar);

        rbCredito = findViewById(R.id.rbCredito);
        rbDebito = findViewById(R.id.rbDebito);


        informacoesApp = (InformacoesApp) getApplicationContext();



        // setando td
        etnomeCompra.setText("Laura Beatriz Wermuth");
        etcpfcompra.setText("031.710.640-65");
        etcelularCompra.setText("(51) 997076246");
        etnumerocartao.setText("1111222233334444");

        etCepCompra.setText("96820-002");
        etRuaComprar.setText("Gaspar Silveira Martins");
        etBairroComprar.setText("Verena");
        etNumCasa.setText("284");
        etComplementoComprar.setText(" ");

        rbCredito.isChecked();
        SpNomeCartao.setSelection(2);
        SpUf.setSelection(2);




        float precoTotal = 0;

        for(int x = 0; x < informacoesApp.lstCarrinho.size(); x++){
            Ingresso ingresso = informacoesApp.lstCarrinho.get(x);

            precoTotal += ingresso.getCadeira().getSetor().getPrecosetor();

        }

        tvprecototalcompra.setText(Float.toString(precoTotal));


        btConfirmarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etnomeCompra.getText().toString().equals("")) {
                    if (!etcpfcompra.getText().toString().equals("")) {
                        if (!etcelularCompra.getText().toString().equals("")) {
                            if (SpNomeCartao.getSelectedItemPosition() > 0) {
                                if (rbCredito.isChecked() || rbDebito.isChecked()) {
                                    if (!etnumerocartao.getText().toString().equals("")) {
                                        if (!etCepCompra.getText().toString().equals("")) {
                                            if (!etRuaComprar.getText().toString().equals("")) {
                                                if (!etBairroComprar.getText().toString().equals("")) {
                                                    if (!etNumCasa.getText().toString().equals("")) {
                                                        if (SpUf.getSelectedItemPosition() > 0) {

                                                                String tipocartao;

                                                                if(rbCredito.isChecked()){
                                                                    tipocartao = "C";
                                                                }else{
                                                                    tipocartao = "D";
                                                                }


                                                                DadosCompra dadosCompra = new DadosCompra(
                                                                        informacoesApp.user.getIdusuario(),
                                                                        etnomeCompra.getText().toString(),
                                                                        etcpfcompra.getText().toString(),
                                                                        etcelularCompra.getText().toString(),
                                                                        SpNomeCartao.getSelectedItem().toString(),
                                                                        tipocartao,
                                                                        etnumerocartao.getText().toString(),
                                                                        etCepCompra.getText().toString(),
                                                                        etRuaComprar.getText().toString(),
                                                                        Integer.parseInt(etNumCasa.getText().toString()),
                                                                        etBairroComprar.getText().toString(),
                                                                        etComplementoComprar.getText().toString(),
                                                                        SpUf.getSelectedItem().toString()
                                                                );

                                                            //Toast.makeText(informacoesApp, ":)" + dadosCompra.toString(), Toast.LENGTH_LONG).show();



                                                            Thread t = new Thread(){
                                                                @Override
                                                                public void run() {
                                                                    ConexaoController ccont = new ConexaoController(informacoesApp);

                                                                    ccont.dadosCompraInserir(dadosCompra);

                                                                    DadosCompra dadoscompraid = ccont.dadosCompraCliente(informacoesApp.user.getIdusuario());


                                                                    boolean ok = false;

                                                                    for(int x = 0; x < informacoesApp.lstCarrinho.size(); x++){

                                                                        Ingresso ingresso = informacoesApp.lstCarrinho.get(x);

                                                                        Date date = new Date();

                                                                        CompraIngresso compraIngresso = new CompraIngresso(ingresso.getIdingresso(),dadoscompraid.getIddadoscompra(), date);

                                                                        ok = ccont.compraIngressoInserir(compraIngresso);


                                                                    }

                                                                    if(ok){


                                                                        runOnUiThread(new Runnable() {
                                                                            @Override
                                                                            public void run() {

                                                                                Toast.makeText(informacoesApp, "Compra Efetuada!", Toast.LENGTH_SHORT).show();

                                                                                informacoesApp.lstCarrinho.clear();
                                                                                Intent it = new Intent(EfetuarCompra.this, home.class);
                                                                                startActivity(it);

                                                                            }
                                                                        });

                                                                    }




                                                                }
                                                            };

                                                            t.start();









                                                        } else {
                                                            Toast.makeText(informacoesApp, "Selecione uma UF", Toast.LENGTH_SHORT).show();
                                                            SpUf.requestFocus();
                                                        }
                                                    } else {
                                                        etNumCasa.setError("Preencha!!");
                                                        etNumCasa.requestFocus();
                                                    }
                                                } else {
                                                    etBairroComprar.setError("Preencha!!");
                                                    etBairroComprar.requestFocus();
                                                }
                                            } else {
                                                etRuaComprar.setError("Preencha!!");
                                                etRuaComprar.requestFocus();
                                            }
                                        } else {
                                            etCepCompra.setError("Preencha!!");
                                            etCepCompra.requestFocus();
                                        }
                                    } else {
                                        etnumerocartao.setError("Preencha!!");
                                        etnumerocartao.requestFocus();
                                    }
                                } else {
                                    Toast.makeText(informacoesApp, "Selecione a forma de pagamento", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(informacoesApp, "Selecione a Bandeira do cartão", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            etcelularCompra.setError("Preencha!!");
                            etcelularCompra.requestFocus();
                        }
                    } else {
                        etcpfcompra.setError("Preencha!!");
                        etcpfcompra.requestFocus();
                    }
                } else {
                    etnomeCompra.setError("Preencha!!");
                    etnomeCompra.requestFocus();
                }


            }
        });

        //puxa o array das bandeiras de cartao para rodar na tela
        ArrayAdapter<CharSequence> adapterCartao = ArrayAdapter.createFromResource(this, R.array.operadoras, android.R.layout.simple_spinner_item);
        adapterCartao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpNomeCartao.setAdapter(adapterCartao);
        //puxa o array das UF para rodar na tela
        ArrayAdapter<CharSequence> adapterUF = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapterUF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpUf.setAdapter(adapterUF);


    }

    private void atualizaLista() {
        ingressoCompraAdapter = new IngressoCompraAdapter(informacoesApp.lstCarrinho);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(EfetuarCompra.this);
        rvDadosCompra.setLayoutManager(mLayoutManager);
        rvDadosCompra.setItemAnimator(new DefaultItemAnimator());
        rvDadosCompra.setAdapter(ingressoCompraAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

}