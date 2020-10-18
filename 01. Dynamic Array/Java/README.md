# Dynamic Array

Array have a fixed size (may not be true in some programming languages such as JavaScript) that cannot be changed once initialized.

```java
// Intitializing array of length 5 with the elements 0, 1, 2, 3, 4
int arr[] = {0,1,2,3,4};

// Trying to 6th element.
arr[5] = 5;
```

But there may be a requirement where we don't know the size of the array while creating it, or it might be the case that we need to
add more elements to the array than expected at the time of initialization.

That is where DynamicArray comes in. It does not have a fixed size and will resize itself whenever required. 
It uses a fixed-sized array under the hood.

## Features

- Dynamically resize itself whenever required.
- The size of the array can be unknown while initializing.
- Can be implemented for any data type.

## Terminologies
- `size`: It represents the no of elements in the array.
- `capacity`: It represents how many elements the current array is capable of storing.
- `arr`: The underlying array.

## Operations

### Initialization

When initializing a dynamic array, we create an underlying static array with a default capacity or the given capacity.

Assuming the default capacity is `5` then, the underlying array will be: 

![Initial Array](./images/initial-array.png)

### Adding elements (push operation)

> **Complexity:**
>
> Average case: O(1), when array does not need resizing.
>
> Worst case: O(n), when array needs resizing.

Adding elements in a dynamic array until the `size` < `capacity` is similar to adding elements to a static array.

The below figure shows adding elements until `size` < `capacity`.

![Adding Elements](./images/push.png)

Now since the `size` > `capacity - 1`, i.e., the underlying array is full. If we want to add more elements, then we first need to increase the capacity of our array. We do this by doing a resize operation.

### Resizing (Increasing the capacity)

> **Complexity:** O(n)

In this example, we will just double the current capacity.

We do this by creating a new array with the new capacity and copy all the elements from the old array to the new array. 
After copying all the elements, we discard the old array and use the new array moving forward.

![Resizing Array](./images/resize.png)

After resizing we can add more elements to the array.

![Adding Elements](./images/push-2.png)
