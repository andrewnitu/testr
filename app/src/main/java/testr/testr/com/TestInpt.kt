package testr.testr.com

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_test_inpt.*



class TestInpt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_inpt)
        setSupportActionBar(toolbar)

        val questionNumber = 0
        val questions = arrayListOf<Question>()

        fab.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Question " + questionNumber)
            builder.setMessage("Please select an answer:")
            val input = EditText(this)
            builder.setView(input)


            builder.setPositiveButton("OK") { dialog, which ->
                run {
                    val q = Question(questionNumber, input.text.toString())
                    questions.add(q)
                }
            }
            builder.setNegativeButton("Cancel", null)

            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
        }

        val gridView = findViewById<View>(R.id.gridview) as GridView
        val testAdapter = TestAdapter(this, questions)
        gridView.adapter = testAdapter
    }

}
