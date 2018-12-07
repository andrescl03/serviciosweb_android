package r.andres.serviciosweb_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import r.andres.serviciosweb_android.utilitarios.Constantes;

public class panelAdmin extends AppCompatActivity implements View.OnClickListener , Response.Listener<String>, Response.ErrorListener{


    String codigoObtenidoIntent;
    RequestQueue request;
    StringRequest StringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_admin);
        obtenerElementosIntent();

        request = Volley.newRequestQueue(this);
        cargarWebService();


    }

    public void obtenerElementosIntent(){

        Bundle datos = this.getIntent().getExtras();
        codigoObtenidoIntent = datos.getString(Constantes.CAMPO_USUARIOADMIN);

    }
    @Override
    public void onClick(View v) {

    }
    public void cargarWebService() {


        String URL = "http://192.168.1.41:8080/Rest_Servicio/rest/restorant/verMisEncuestas?"
                + "cod="+codigoObtenidoIntent;

        URL = URL.replace(" ", "%20");
        StringRequest = new StringRequest(Request.Method.GET, URL, this, this);
        request.add(StringRequest);


    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


    @Override
    public void onResponse(String response) {
    }




}
