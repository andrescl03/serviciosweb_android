package r.andres.serviciosweb_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import r.andres.serviciosweb_android.utilitarios.singleToast;

public class registroAdmin extends AppCompatActivity implements View.OnClickListener ,  Response.Listener<String>, Response.ErrorListener {

    Button  btnRRegistroU;
    EditText txtRCodigo, txtRClave;
    RequestQueue request;
    StringRequest StringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_admin);
        obtenerRecursos();

        request = Volley.newRequestQueue(this);
        btnRRegistroU.setOnClickListener(this);

    }




        public void obtenerRecursos(){
            btnRRegistroU = findViewById(R.id.btnRRegistroU);
            txtRCodigo = findViewById(R.id.txtRCodigo);
            txtRClave = findViewById(R.id.txtRClave);


        }
    @Override
    public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnRRegistroU:

                        if(txtRCodigo.getText().toString().trim().isEmpty() ||txtRClave.getText().toString().trim().isEmpty()){
                            singleToast.show(this,"Por favor complete los campos");
                        }
                        else {
                            cargarWebService();
                        }

                        break;

                }
    }

    public void cargarWebService(){


        String URL = "http://192.168.1.41:8080/Rest_Servicio/rest/restorant/query3?"
                + "correo=" + txtRCodigo.getText().toString()
                + "&pass=" + txtRClave.getText().toString();

        URL = URL.replace(" ", "%20");
        StringRequest = new StringRequest(Request.Method.GET, URL, this, this);
        request.add(StringRequest);

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se ha podido registrar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(this,"Se registro correctamente un nuevo Administrador", Toast.LENGTH_SHORT).show();
        Intent panelMain = new Intent(this,MainActivity.class);
        startActivity(panelMain);
    }
}
