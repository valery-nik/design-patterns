# functional style

1 Functional vs imperative approach
   * Функциональный код обладает декларативностью, т.е. "что я хочу, а не как хочу"
   * Легче придерживаться DRY, даже на уровне передаваемых в метод параметров. (https://youtu.be/rNg8jrWelXk?t=1067)
   
2 Терминология
  * **Функция первого класса** - функция котороя может представлена как аргумент для другой функции, результат другой функции
    или быть присвоена переменной.   
    
  ```java
  public int sum(int x, int y) {
    return x + y;
  }
  
  BiFunction<Integer, Integer, Integer> sum = this.sum;
  ```
  
  * **Функция высшего порядка** - функция которая принимает/возвращаеь в качестве результата другую функцию.
  
  ```java
  /**
  * Фунция decorate - функция высшего порядка 
  */
  public Runnable decorate(Runnable runnable) {
    return () -> {
        long time = System.currentTimeMillis();
        try {
            runnable.run();
        } finally {
            System.out.println("Exec tume:" + (System.currentTimeMillis() - time));
        }
    }  
  }
  ```
  
  * **Частичное применение фунции** - процесс фиксации части аргументов функции, который создает другую функцию меньшей
   арности.