package testr.testr.com

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_test_input.*
import kotlinx.android.synthetic.main.layout_answer.view.*
import java.util.*


class TestInput : AppCompatActivity(), Observer {
    override fun update(p0: Observable?, p1: Any?) {
        // TODO: not implemented
    }

    private lateinit var mModel: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_input)
        setSupportActionBar(toolbar)

        mModel = Model.getInstance()
        mModel.addObserver(this)

        mModel.initObservers()

        var questionNumber = 1
        val questions = arrayListOf<Question>()

        fab.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Question " + questionNumber)
            builder.setMessage("Please select an answer:")
            val input = EditText(this)
            input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1), InputFilter.AllCaps())
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, which ->
                run {
                    val q = Question(questionNumber, input.text.toString())
                    questions.add(q)
                    questionNumber++
                }
            }
            builder.setNegativeButton("Cancel", null)

            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
        }

        val submitButton = findViewById<Button>(R.id.submit)

        submitButton.setOnClickListener { view ->
            mModel.setCorrectAnswerSet(questions)

            val myIntent = Intent(this, CameraActivity::class.java)
            startActivity(myIntent)
        }

        val gridView = findViewById<View>(R.id.gridview) as GridView
        val testAdapter = TestAdapter(this, questions)
        gridView.adapter = testAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Question " + view.question_number.text)
            builder.setMessage("Please select an answer:")
            val input = EditText(this)
            input.setText(view.answer.text)
            input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1), InputFilter.AllCaps())
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, which ->
                run {
                    val q = Question(questionNumber, input.text.toString())
                    questions.set(Integer.parseInt(view.question_number.text.toString()) - 1, Question(Integer.parseInt(view.question_number.text.toString()), input.text.toString()))
                }
            }
            builder.setNeutralButton("Delete") {dialog, which ->
                run {
                    questions.removeAt(Integer.parseInt(view.question_number.text.toString()) - 1)
                    questionNumber--
                    for (q in questions) {
                        if (q.getNumber() > Integer.parseInt(view.question_number.text.toString()) - 1) {
                            q.setNumber(q.getNumber() - 1)
                        }
                    }
                    val testAdapter = TestAdapter(this, questions)
                    gridView.adapter = testAdapter
                }
            }
            builder.setNegativeButton("Cancel", null)

            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
        }
    }

}
