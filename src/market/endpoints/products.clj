(ns market.endpoints.products
  (:require [market.handlers.products :as handler]
            [clojure.data.json :as json]))


(defn create-products
  [req]
  {:body (handler/create-products req) :status 201})

(defn view-products
  [req]
  (let [res (handler/view-products req)]
    {:body (json/write-str res) :status 200}))

(defn list-products
  [req]
  (let [res (handler/list-products req)]
    (println (json/write-str res))
    {:body (json/write-str res) :status 200}))

(defn update-products
  [req]
  (let [res (handler/update-products req)]
    {:body (json/write-str res) :status 200}))

(defn delete-products
  [req]
  (let [res (handler/delete-products req)]
    {:body (json/write-str res) :status 200}))

