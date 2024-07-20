<?php

use App\Http\Controllers\ProductController;
use App\Http\Controllers\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::post('/register' ,[ UserController::class,'register']);
Route::post('/login' ,[ UserController::class,'login']);

Route::post('/product/addProduct' ,[ ProductController::class,'addProduct']);
Route::delete('/product/delete/{id}' ,[ ProductController::class,'deleteProduct']);
Route::get('/product/list' ,[ ProductController::class,'listProduct']);
Route::get('/product/get/{id}' ,[ ProductController::class,'getProduct']);

Route::post('/product/update', [ProductController::class,'updateProduct']);





Route::post('/test' ,[ ProductController::class,'test']);
