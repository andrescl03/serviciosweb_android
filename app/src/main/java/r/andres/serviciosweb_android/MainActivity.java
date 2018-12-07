package r.andres.serviciosweb_android;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import r.andres.serviciosweb_android.entidades.usuarioBean;
import r.andres.serviciosweb_android.utilitarios.Constantes;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener , Response.Listener<String>, Response.ErrorListener{


    Button btnIniciarSesion;
    Button btnRegistrarse;
    EditText txtMCorreo;
    EditText txtMClave;

    RequestQueue request;
    StringRequest StringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamando los recursos XML-Logica
        llamandoRecursos();
        btnIniciarSesion.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);
        request = Volley.newRequestQueue(this);
    }

    public void llamandoRecursos(){
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        txtMCorreo = findViewById(R.id.txtMCorreo);
        txtMClave = findViewById(R.id.txtMClave);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnIniciarSesion:
                  cargarWebService();
                    break;

                case R.id.btnRegistrarse:
                    break;

            }
    }
    public void cargarWebService() {


        String URL = "http://192.168.1.41:8080/Rest_Servicio/rest/restorant/login?"
                + "cod=" + txtMCorreo.getText().toString()
                + "&pass=" + txtMClave.getText().toString();

        URL = URL.replace(" ", "%20");
        StringRequest = new StringRequest(Request.Method.GET, URL, this, this);
        request.add(StringRequest);


    }
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"Datos incorrectos" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {




            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            String datoObtenido = response;
            ArrayList<JSONObject> lista = new ArrayList<JSONObject>();
            usuarioBean objUsuario = new usuarioBean();


            try {

                JSONArray jsonArray = new JSONArray(datoObtenido);
                lista.add(jsonArray.getJSONObject(0));
                objUsuario.setCodUsuario(lista.get(0).getString("cod"));

                if( objUsuario.getCodUsuario() == null){
                    Toast.makeText(this,"Datos incorrectos" ,Toast.LENGTH_SHORT).show();
                return;
                }

                else{
                    Intent panel = new Intent(this, panelAdmin.class);
                    panel.putExtra(Constantes.CAMPO_USUARIOADMIN, objUsuario.getCodUsuario());
                    startActivity(panel);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }



        }



}

