package ma.sara.myfirstapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView


private const val POURCENTAGE_INITIAL = 15

class MainActivity : AppCompatActivity() {
    private lateinit var etMontantFacture: EditText
    private lateinit var seekBarPourcentage: SeekBar
    private lateinit var tvCommissionPourcentage: TextView
    private lateinit var tvCommissionValeur: TextView
    private lateinit var tvTotalvaleur: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMontantFacture = findViewById(R.id.etMontantFacture)
        seekBarPourcentage = findViewById(R.id.seekBarPourcentage)
        tvCommissionPourcentage = findViewById(R.id.tvCommissionPourcentage)
        tvCommissionValeur = findViewById(R.id.tvCommissionValeur)
        tvTotalvaleur = findViewById(R.id.tvTotalvaleur)

        tvCommissionPourcentage.text = "$POURCENTAGE_INITIAL%"
        seekBarPourcentage.progress = POURCENTAGE_INITIAL

        tvCommissionValeur.text = ""
        tvTotalvaleur.text = ""

        seekBarPourcentage.setOnSeekBarChangeListener(object:OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tvCommissionPourcentage.text = "$p1%"
                calculerEtMiseAjourUI()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })




        etMontantFacture.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
               if(p0.isNullOrEmpty()) {
                   tvCommissionValeur.text = ""
                   tvTotalvaleur.text = ""
                   return
               }
                calculerEtMiseAjourUI()
            }

        })

    }

    private fun calculerEtMiseAjourUI() {
        var montantInitial = etMontantFacture.text.toString().toDouble()
        var commissionPourcentage = seekBarPourcentage.progress

        var commission = montantInitial * commissionPourcentage / 100
        var total = montantInitial + commission

        //Update the UI
        tvCommissionValeur.text = commission.toString()
        tvTotalvaleur.text = total.toString()
    }


}