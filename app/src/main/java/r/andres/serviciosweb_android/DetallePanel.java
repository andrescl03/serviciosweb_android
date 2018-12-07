package r.andres.serviciosweb_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import r.andres.serviciosweb_android.entidades.usuarioBean;
import r.andres.serviciosweb_android.utilitarios.Constantes;

public class DetallePanel extends AppCompatActivity implements View.OnClickListener , Response.Listener<String>, Response.ErrorListener{

    Button btnDAgregarP;
    Button btnDGrabar;
    TextView txtDUsuario;
    EditText txtDIdentificador,txtDDescripcion,txtDPreguntas;
    TableLayout tableDPreguntas;

    List<String> datos = new ArrayList<>();
    RequestQueue request;
    StringRequest StringRequest;

    String codigoObtenidoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_panel);

        llamadaRecursos();
        obtenerElementosIntent();
        txtDUsuario.setText(codigoObtenidoIntent);

        btnDGrabar.setOnClickListener(this);
        btnDAgregarP.setOnClickListener(this);
        request = Volley.newRequestQueue(this);

        tableDPreguntas.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

    }



    public void llamadaRecursos(){
        btnDAgregarP = findViewById(R.id.btnDAgregarP);
        btnDGrabar = findViewById(R.id.btnDGrabar);
        txtDUsuario = findViewById(R.id.txtDUsuario);
        txtDIdentificador  = findViewById(R.id.txtDIdentificador);
        txtDDescripcion = findViewById(R.id.txtDDescripcion);
        txtDPreguntas = findViewById(R.id.txtDPreguntas);
        tableDPreguntas = findViewById(R.id.tableDPreguntas);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnDAgregarP:
                AgregarPregunta();


                break;


            case R.id.btnDGrabar:

                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("Alerta");
                dialogo.setMessage("¿Desea grabar las preguntas?");
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        aceptarRegistro();
                    } });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        cancelarRegistro();
                    }
                });
                dialogo.show();



                break;
        }

    }
    public void aceptarRegistro(){
        cargarWebService();
        Intent intentPrincipal = new Intent(this,panelAdmin.class);
        intentPrincipal.putExtra(Constantes.CAMPO_USUARIOADMIN,codigoObtenidoIntent);
        startActivity(intentPrincipal);
        finish();
    }
    public void cancelarRegistro() {
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }

    public void cargarWebService(){


        String URL1 = "http://192.168.1.41:8080/Rest_Servicio/rest/restorant/iniciaEncuesta?"
                + "ide="+txtDIdentificador.getText().toString()+ "&usu="+txtDUsuario.getText().toString()+ "&des="+txtDDescripcion.getText().toString()+"&datos="+datos;
        URL1 = URL1.replace(" ", "%20");
        StringRequest = new StringRequest(Request.Method.GET, URL1, this, this);
        request.add(StringRequest);

    }

    public void AgregarPregunta(){
        TableRow tableRow = new TableRow(this);
        tableDPreguntas.addView(tableRow);

        TextView objText = new TextView(this);
        objText.setTextColor(getResources().getColor(R.color.colorIcons));
        objText.setTextSize(20);

        objText.setText("Pregunta: " +txtDPreguntas.getText());
        datos.add(txtDPreguntas.getText().toString());
        tableRow.addView( objText);
        //Limpio el casillero
        txtDPreguntas.setText("");
    }


    public void obtenerElementosIntent(){
        Bundle datos = this.getIntent().getExtras();
        codigoObtenidoIntent = datos.getString(Constantes.CAMPO_USUARIOADMIN);

    }

    @Override public void onBackPressed() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Alerta");
        dialogo.setMessage("¿Desea salir del registro de preguntas?");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                aceptar();
            } });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                cancelar();
            }
        });
        dialogo.show();
    }

    public void aceptar(){
        Intent intentPrincipal = new Intent(this,panelAdmin.class);
        intentPrincipal.putExtra(Constantes.CAMPO_USUARIOADMIN,codigoObtenidoIntent);
        startActivity(intentPrincipal);
        finish();
    }
    public void cancelar() {
    }




}
