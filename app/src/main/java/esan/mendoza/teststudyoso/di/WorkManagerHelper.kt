package esan.mendoza.teststudyoso.di

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.lifecycle.Observer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.work.OneTimeWorkRequestBuilder
import esan.mendoza.teststudyoso.MainActivity
import java.util.UUID
import java.util.concurrent.TimeUnit

class WorkManagerHelper(private val context: Context) {

    private val worker: WorkManager = WorkManager.getInstance(context)

    init {
        // Programar la verificación periódica de tareas
        NotificationWorker.programarVerificacionTareas(context)
    }

    // Método para configurar y ejecutar el trabajo
    fun executeWork(message: String, textInput: MutableState<String>) {
        val powerConstraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val taskData = Data.Builder().putString(MainActivity.MESSAGE_STATUS, message).build()

        val request: WorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setConstraints(powerConstraints)
            .setInputData(taskData)
            .build()

        observeWork(request.id, textInput)
        worker.enqueue(request)
    }

    // Método para verificar tareas con vencimiento próximo o vencidas
    fun verificarTareasVencidas() {
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(0, TimeUnit.MILLISECONDS) // Ejecutar inmediatamente
            .build()
        worker.enqueue(request)
    }

    // Método para observar el estado del trabajo
    private fun observeWork(workId: UUID, textInput: MutableState<String>) {
        worker.getWorkInfoByIdLiveData(workId).observeForever { workInfo ->
            workInfo?.let {
                if (it.state.isFinished) {
                    val outputData = it.outputData
                    val taskResult = outputData.getString(NotificationWorker.WORK_RESULT)
                    taskResult?.let { result ->
                        textInput.value = result
                    }
                } else {
                    val workStatus = workInfo.state
                    textInput.value = workStatus.toString()
                }
            }
        }
    }
}