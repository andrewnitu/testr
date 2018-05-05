package testr.testr.com

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_test_inpt.*

class TestInpt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_inpt)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val questions = arrayOf<Question>()

        val gridView = findViewById<View>(R.id.gridview) as GridView
        val testAdapter = TestAdapter(this, questions)
        gridView.adapter = testAdapter
    }

}
