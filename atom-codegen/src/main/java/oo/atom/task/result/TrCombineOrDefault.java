/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package oo.atom.task.result;

import java.util.function.BinaryOperator;
import io.vavr.collection.List;

class TrCombineOrDefaultInference<T> implements TaskResult.Inference<T> {
    private final BinaryOperator<T> combineOperator;
    private final T defaultValue;
    private final List<TaskResult<T>> taskResults;

    public TrCombineOrDefaultInference(BinaryOperator<T> combineOperator, T defaultValue, List<TaskResult<T>> taskResults) {
        this.combineOperator = combineOperator;
        this.defaultValue = defaultValue;
        this.taskResults = taskResults;
    }

    @Override
    public final TaskResult<T> taskResult() {
        if(taskResults.isEmpty()) {
            return new TrSuccess<>(defaultValue);
        } else {
            return new TrCombined<>(combineOperator, taskResults);
        }
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class TrCombineOrDefault<T> extends TrInferred<T> {
    public TrCombineOrDefault(BinaryOperator<T> combineOperator, T defaultValue, List<TaskResult<T>> taskResults) {
        super(
            new TrCombineOrDefaultInference(
                combineOperator,
                defaultValue,
                taskResults
            )
        );
    }
    
    public TrCombineOrDefault(BinaryOperator<T> combineOperator, T defaultValue, TaskResult<T>... taskResults) {
        this(
            combineOperator,
            defaultValue,
            List.of(taskResults)
        );
    }
}