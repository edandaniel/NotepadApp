package com.edandaniel.notepadapp.view.form

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.edandaniel.notepadapp.R
import com.edandaniel.notepadapp.model.ResponseStatus
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    lateinit var formViewModel: FormViewModel
    //similar too: var formViewModel: formViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        formViewModel =  ViewModelProviders.of(this)
                .get(FormViewModel::class.java)

        formViewModel.responseStatus.observe(this,
                responseStatusObserver)

        btSave.setOnClickListener{
            formViewModel.save(
                    etTitle.text.toString(),
                    etDescription.text.toString()
            )
        }
    }

    private var responseStatusObserver = Observer<ResponseStatus>{
        if(it?.success == true){
            setResult(Activity.RESULT_OK)
            finish() // go back to the previous screen
        }else{
            Toast.makeText(this,
                    it?.message,
                    Toast.LENGTH_SHORT).show()
        }
    }
}
