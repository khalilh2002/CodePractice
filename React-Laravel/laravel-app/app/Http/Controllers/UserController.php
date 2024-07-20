<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{

    function register( Request $request ){
        
        $user = new User;
        $user->name = $request->input('name');
        $user->email = $request->input('email');
        $user->password = Hash::make( $request->input('password') );
        $user->save();
        return response()->json([
            "name"=> $user->name ,      
            "email"=>  $user->email,
            "password"=> $user->password
        ]); 
    }

    function login(Request $request)  {
        $user = User::where('email',$request->input('email'))->first();
        
        if (!$user || !Hash::check($request->input('password'), $user->password)) {
           return json_encode([
            'error'=>'email or password are incorrect'
        ]);
        }

        return $user;
    }

}

