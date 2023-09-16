package com.example.pulmonaryrehabilitation.pdf

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import java.io.File
import java.io.FileOutputStream

object PDFGenerator {
    private const val LOG_TAG = "PDFGenerator"
    private val currentTimeStamp = CurrentUser.getCurrentDateTime().toLong()
    val stringFilePath = Environment.getExternalStorageDirectory().path + "/Download/LungFit_${CurrentUser.parseDateForFileName(currentTimeStamp)}.pdf"
    private val file = File(stringFilePath)
    private const val page_x_coordinate = 50f
    private var page_y_coordinate = 0f
    private const val pageWidth = 612
    private const val pageHeight = 792
    private const val pageEnd = 720
    private var pageNumber = 1

    private val qs = CurrentUser.getQuestionnaireHistory()
    private val mMRCData = CurrentUser.getmMRCHistory()
    private val CATData = CurrentUser.getCATHistory()
    private val stepData = CurrentUser.getStepHistory()

    private lateinit var document: PdfDocument
    private lateinit var currentPage: PdfDocument.Page
    private lateinit var canvas: Canvas

    /**
     * Creates a PDF report based on user data and saves it to a file.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - A PDF report is created and saved to a file.
     *
     * @return a task that will complete when the PDF report is finished being created and saved
     */
    fun createPDF(): Task<Unit> {
        val task = TaskCompletionSource<Unit>()
        document = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
        val page = document.startPage(pageInfo)
        currentPage = page
        canvas = currentPage.canvas

        createHeader(canvas)
        createName(canvas)
        createDate(canvas)
        createGeneralSatisfactionReport()
        createmMRCReport(canvas)
        createCATReport(canvas)
        createStepHistoryReport(canvas)

        document.finishPage(currentPage)

        try {
            document.writeTo(FileOutputStream(file))
            document.close()
            task.setResult(Unit)
        } catch (e: java.lang.Exception) {
            Log.e(LOG_TAG, e.toString())
            task.setException(e)
        }
        return task.task
    }

    /**
     * Creates the header of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The header of the PDF report is created on the canvas.
     *
     * @param canvas the canvas on which to create the header
     */
    private fun createHeader(canvas: Canvas) {
        val headerPaint = Paint()
        headerPaint.textSize = 24f
        headerPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        val headerText = "LungFit User Report"
        canvas.drawText(headerText, 200f, 50f, headerPaint)
    }

    /**
     * Creates the name section of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The name section of the PDF report is created on the canvas.
     *
     * @param canvas the canvas on which to create the name section
     */
    private fun createName(canvas: Canvas) {
        val namePaint = Paint()
        namePaint.textSize = 13f
        val nameText = "Name: ${CurrentUser.getUsername()}"
        canvas.drawText(nameText, page_x_coordinate, 100f, namePaint)
    }

    /**
     * Creates the date section of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The date section of the PDF report is created on the canvas.
     *
     * @param canvas the canvas on which to create the date section
     */
    private fun createDate(canvas: Canvas) {
        val datePaint = Paint()
        val timestamp = CurrentUser.getCurrentDateTime().toLong()
        datePaint.textSize = 13f
        val nameText = CurrentUser.parseDate(timestamp)
        canvas.drawText(nameText, 520f, 50f, datePaint)
    }

    /**
     * Creates the general satisfaction questionnaire report section of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The general satisfaction report section of the PDF report is created on the canvas.
     */
    private fun createGeneralSatisfactionReport() {
        val gsTextStart = 130f
        page_y_coordinate = gsTextStart + 20f
        val gsPaint = Paint()
        gsPaint.textSize = 13f
        val gsText = "Your Exercise Satisfaction Ratings:"
        canvas.drawText(gsText, page_x_coordinate, gsTextStart, gsPaint)
        for (entry in qs) {
            val key = CurrentUser.parseDate(entry.key.toLong())
            val value = entry.value as Map<*, *>
            val text = "$key: ${value["answer"]}"
            canvas.drawText(text, page_x_coordinate, page_y_coordinate, gsPaint)
            page_y_coordinate += 20f // increase y position for next line
            if (page_y_coordinate >= pageEnd) {
                createNewPage()
            }
        }
        Log.d(LOG_TAG, "end of createGeneralSatisfactionReport: $page_y_coordinate")
    }

    /**
     * Creates the mMRC report section of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The mMRC report section of the PDF report is created on the canvas.
     *
     * @param canvas the canvas on which to create the mMRC report section
     */
    private fun createmMRCReport(canvas: Canvas) {
        Log.d(LOG_TAG, "start of createmMRCReport: $page_y_coordinate")
        val mMRCTextStart = page_y_coordinate
        page_y_coordinate = mMRCTextStart + 20f
        val mMRCPaint = Paint()
        mMRCPaint.textSize = 13f
        val mMRCText = "Your mMRC Score History:"
        canvas.drawText(mMRCText, page_x_coordinate, mMRCTextStart, mMRCPaint)
        for (entry in mMRCData) {
            val key = CurrentUser.parseDate(entry.key.toLong())
            val value = entry.value as Map<*, *>
            val text = "$key: ${value["grade"]}"
            canvas.drawText(text, page_x_coordinate, page_y_coordinate, mMRCPaint)
            page_y_coordinate += 20f // increase y position for next line
            if (page_y_coordinate >= pageEnd) {
                createNewPage()
            }
        }
        Log.d(LOG_TAG, "end of createmMRCReport: $page_y_coordinate")
    }

    /**
     * Creates the CAT report section of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The CAT report section of the PDF report is created on the canvas.
     *
     * @param canvas the canvas on which to create the CAT report section
     */
    private fun createCATReport(canvas: Canvas) {
        Log.d(LOG_TAG, "start of createCATReport: $page_y_coordinate")
        val CatTextStart = page_y_coordinate
        page_y_coordinate = CatTextStart + 20f
        val CatPaint = Paint()
        CatPaint.textSize = 13f
        val CatText = "Your COPD Assessment Test Score History:"
        canvas.drawText(CatText, page_x_coordinate, CatTextStart, CatPaint)
        for (entry in CATData) {
            val key = CurrentUser.parseDate(entry.key.toLong())
            val value = entry.value as String // get the total score only
            val extractedTotal = extractCatTotalScore(value)
            val text = "$key: $extractedTotal"
            canvas.drawText(text, page_x_coordinate, page_y_coordinate, CatPaint)
            page_y_coordinate += 20f // increase y position for next line
            if (page_y_coordinate >= pageEnd) {
                createNewPage()
            }
        }
        Log.d(LOG_TAG, "end of createCATReport: $page_y_coordinate")
    }

    /**
     * Creates the step history report section of the PDF report.
     *
     * Preconditions:
     * - The canvas is initialized.
     *
     * Postconditions:
     * - The step history report section of the PDF report is created on the canvas.
     *
     * @param canvas the canvas on which to create the step history report section
     */
    private fun createStepHistoryReport(canvas: Canvas) {
        Log.d(LOG_TAG, "start of createStepHistoryReport: $page_y_coordinate")
        val stepTextStart = page_y_coordinate
        page_y_coordinate = stepTextStart + 20f
        val stepPaint = Paint()
        stepPaint.textSize = 13f
        val stepText = "Your Step History:"
        canvas.drawText(stepText, page_x_coordinate, stepTextStart, stepPaint)
        for (entry in stepData) {
            val key = entry.key
            val value = entry.value as Map<*, *>
            val text = "$key: ${value["stepCount"]}"
            canvas.drawText(text, page_x_coordinate, page_y_coordinate, stepPaint)
            page_y_coordinate += 20f // increase y position for next line
            if (page_y_coordinate >= pageEnd) {
                createNewPage()
            }
        }
        Log.d(LOG_TAG, "end of createStepHistoryReport: $page_y_coordinate")
    }

    /**
     * Extracts the total score from a CAT test result string.
     *
     * Preconditions:
     * - The input string is a valid CAT test result string.
     *
     * Postconditions:
     * - None.
     *
     * @param resultString the CAT test result string from which to extract the total score
     * @return the total score extracted from the CAT test result string
     */
    private fun extractCatTotalScore(input: String): Int {
        val pattern = "\\{TOTAL=(\\d+),".toRegex()
        val matchResult = pattern.find(input)
        return matchResult?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }

    /**
     * Creates a new page in the PDF report.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - A new page is added to the PDF report.
     */
    private fun createNewPage() {
        document.finishPage(currentPage)

        val newPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber + 1).create()
        val newPage = document.startPage(newPageInfo)
        currentPage = newPage
        page_y_coordinate = 50f
        canvas = currentPage.canvas
    }
}