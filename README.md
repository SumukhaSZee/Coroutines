# Coroutines :

- MainThread is responsible for UI related operations and hence it is called UI Thread.
- If we make the MainThread to execute tasks other than UI related operations, the UI becomes irresponsive causing many frame drops and UI lags.
- This problem can be solved by making use of CoRoutines which are basically the light weight threads and are responsible for carrying out operations which are non UI related and hence allows the MainThread to do its job without any issues.
- Coroutines are a powerful feature in many programming languages, including Python and Kotlin. They allow for **asynchronous programming**, which can greatly increase the efficiency and speed of programs.
- Coroutines are especially useful for **I/O-bound tasks, such as network requests or file operations**, as they allow the program to continue executing other tasks while waiting for the I/O operation to complete.

********CO/ROUTINES - Cooperative Execution of Tasks or Functionalities.********

They are called as **LightWeightThreads.**

Mainly in Coroutines we look at three things :

- Coroutine Scope
- Coroutine Builder
- Coroutine Dispatcher

GlobalScope.launch(Dispatchers.IO) {

```
// Body of Coroutine //

```

}

# How to Start a Coroutine?:

## **Coroutine Scope :**

It is a scope in which the coroutine is active and we can launch the coroutine inside it. CoroutineScope is usually created for a specific task and is cancelled when the task is completed.

> Note : We cannot Launch a coroutine without a scope.
> 

### Types of Coroutine Scopes:

**Global Scope**:

- This scope is created when the app is launched and lasts until the app is closed. Any coroutine launched in this scope will continue to run until it is completed or cancelled.
- It is useful for long-running tasks that need to continue even if the user leaves the app, such as downloading large files or syncing data with a server.

Its not a good practise to use GlobalScope because it keeps on running until the entire app gets destroyed from background.

Ex :

GlobalScope.launch{

//Coroutine Body//

}

**LifeCycleScope:**

- This scope is tied to the lifecycle of a specific component, such as an Activity or Fragment.
- Any coroutine launched in this scope will automatically be cancelled when the component is destroyed, preventing memory leaks and other issues.
- It is recommended to use this scope for coroutines that are directly related to the UI, such as updating views or performing animations.

Ex:

lifecycleScope.launch{

//Coroutine Body//

}

**ViewModelScope**:

- This scope is tied to the lifecycle of a ViewModel in Android architecture components, the coroutine stays until the view model is in use.
- Any coroutine launched in this scope will automatically be cancelled when the ViewModel is destroyed.
- It is recommended to use this scope for coroutines that are related to data processing and manipulation in the ViewModel.

Ex:

viewModelScope.launch{

//Coroutine Body//

}

## Coroutine Builders and Dispatchers:

**Dispatchers**:

What are Dispatchers?

**Dispatchers are objects in Kotlin which determine the execution context of coroutines**. They are responsible for scheduling coroutines for execution on threads and thread pools. There are several types of dispatchers available in Kotlin for coroutines, each with its own specific use case.

- **Dispatchers.Main**: This dispatcher is used for running tasks on the main UI thread.
- **Dispatchers.IO**: This dispatcher is used for running I/O intensive tasks such as network requests(API Calling)  or database operations. **Mainly useful for running background tasks in a separate thread rather than the main thread.**
- **Dispatchers.Default**: This dispatcher is used for running CPU intensive tasks that don't require the UI thread.

**Coroutine Builders**:

**Coroutine Builders**:
Coroutine Builders are used to create coroutines and launch them on a specified Dispatcher. There are three types of coroutine builders:

- **launch**: This builder is used for launching a new coroutine and returns a Job object. It does not return any result.
    
    The Job object is used to keep track of the Coroutine.
    
    > Fire and Forget.
    > 
    
    GlobalScope.launch{
    
    println(”Hello World”);
    
    }
    
- **async**: This builder is used for launching a new coroutine and returns a **Deferred** object. It is used when we need to return a result from the coroutine.
    
    > Async returns something..
    > 
- Async always returns the last statement of the coroutine body.

val defferedjob = GlobalScope.async{

       //Coroutine body

 10

}

println(defferedjob.await())

- **runBlocking**: This builder is used for launching a new coroutine and blocks the current thread until the coroutine finishes. It is mainly used for testing purposes.

We can use these coroutine builders along with Dispatchers to execute tasks on different threads and improve the performance of our app.


