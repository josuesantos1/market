(ns market.handlers.products
  (:require [market.database.repositories.products :as PREPO]))

(defn create-products
  [product]
  (let [body (:json-params product)]
    (PREPO/create-products body)))

(defn view-products
  [product]
  (let [params (:path-params product)
        title (:title params)]
    (PREPO/view-products title)))

(defn list-products
  [product]
  (let [body (:query-params product)]
    (PREPO/list-products body)))

(defn update-products
  [product]
  (let [body (:json-params product)]
    (PREPO/update-products body)))

(defn delete-products
  [product]
  (let [body (:json-params product)]
    (PREPO/delete-products body)))
