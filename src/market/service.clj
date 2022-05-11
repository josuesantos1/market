(ns market.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            
            [market.endpoints.products :as products]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(def common-interceptors [(body-params/body-params) http/html-body])

;; Tabular routes
(def routes #{["/" :get (conj common-interceptors `home-page)]
              ["/about" :get (conj common-interceptors `about-page)]
              ["/products" :get (conj common-interceptors `products/list-products)]
              ["/products/:title" :get (conj common-interceptors `products/view-products)]
              ["/products" :post (conj common-interceptors `products/create-products)]
              ["/products" :patch (conj common-interceptors `products/update-products)]
              ["/products" :delete (conj common-interceptors `products/delete-products)]})


(def service {:env :prod

              ::http/routes routes

              ::http/resource-path "/public"

              ::http/type :jetty

              ::http/port 3000

              ::http/container-options {:h2c? true
                                        :h2? false

                                        :ssl? false

                                        }})
