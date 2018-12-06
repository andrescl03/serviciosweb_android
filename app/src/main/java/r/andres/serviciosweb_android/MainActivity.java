package r.andres.serviciosweb_android;

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

                    break;

                case R.id.btnRegistrarse:
                    break;

            }
    }
    public void cargarWebService() {


        String URL = "http://192.168.43.25:8080/Rest_Servicio_presente/rest/servicios2/query1?"
                + "correo=" + txtMCorreo.getText().toString()
                + "&pass=" + txtMClave.getText().toString();

        URL = URL.replace(" ", "%20");
        StringRequest = new StringRequest(Request.Method.GET, URL, this, this);
        request.add(StringRequest);


    }
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"El usuario no existe en el servidor ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {

        Toast.makeText(this,"El usuario existe en el WebService"  ,Toast.LENGTH_SHORT).show();


    }
}

