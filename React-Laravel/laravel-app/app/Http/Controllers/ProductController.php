<?php

namespace App\Http\Controllers;

use Exception;
use App\Models\product;
use Illuminate\Http\Request;
use Illuminate\Http\JsonResponse;
use Illuminate\Support\Facades\Storage;
use Illuminate\Database\Eloquent\Collection;

class ProductController extends Controller
{
    function addProduct(Request $request): JsonResponse
    {
        try {
            $p = new product();
            $p->name = $request->input('name');
            $p->description = $request->input('description');
            $p->file_path = $request->file("file")->store('products');
            $p->save();
            if ($p == null) {
                return response()->json([
                    "error" => "product has not been saved"
                ], 500);
            }
        } catch (Exception $th) {

            return response()->json([
                "error" => $th->getMessage()
            ], 500);
        }

        return response()->json([
            "ok" => "product saved"
        ], 200);
    }

    function listProduct(): Collection
    {
        return product::all();
    }

    function deleteProduct($id): JsonResponse
    {
        try {
            $product = Product::find($id);

            if ($product === null) {
                return response()->json([
                    "error" => 'no product',
                    "message" => "There is no product with this ID: $id"
                ], 404);
            }

            $msg = '';

            if ($product->file_path !== null) {
                Storage::delete($product->file_path);
                $msg = " , image has been deleted";
            }

            if ($product->delete()) {
                return response()->json([
                    "ok" => "Product has been deleted",
                    "message" => "Product has been deleted" . $msg,
                ], 200);
            } else {
                return response()->json([
                    "error" => 'deletion error',
                    "message" => "There was an error deleting the product with ID: $id"
                ], 500);
            }
        } catch (Exception $e) {
            return response()->json([
                "error" => 'exception',
                "message" => $e->getMessage()
            ], 500);
        }
    }

    function updateProduct(Request $request)
    {
        try {
            $product = product::find($request->input('id'));
            if ($product == null) {
                return response()->json([
                    'error' => "no found"
                ], 404);
            }

            if (!empty($request->input('name'))) {
                $product->name = $request->input('name');
            }
            
            if (!empty($request->input('description'))) {
                $product->description = $request->input('description');
            }

            if (!empty($request->file("file"))) {
                if ($product->file_path !== null) {
                    Storage::delete($product->file_path);
                    $msg = " , image has been deleted";
                }
                $product->file_path = $request->file("file")->store('products');
            }


            $product->save();

            return response()->json([
                'ok' => "has been updated",
                "message" => "the product has been updated"
            ], 200);
        } catch (Exception $th) {
            return response()->json([
                'error' => $th->getMessage()
            ], 500);
        }
    }

    function getProduct($id)
    {
        $product = Product::find($id);

        if (!$product) {
            return response()->json(['error' => "error", 'message' => 'Product not found'], 404);
        }

        return response()->json($product, 200);
    }

    function test(Request $request)
    {
        return $request;
    }
}
