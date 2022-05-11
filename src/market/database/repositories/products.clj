(ns market.database.repositories.products
  (:require [market.database.database :as db]
            [next.jdbc :as jdbc]
            [honey.sql :as sql]
            [honey.sql.helpers :as h]))


(defn- insert-to-dto
  [data]
  (let [title (:title data)
        desc (:description data)
        price (:price data)
        image (:image data)
        amount (:amount data)]
    (-> (h/insert-into :products)
        (h/columns :title :description :price :image :amount)
        (h/values
         [[title desc price image amount]]))))

(defn create-products
  [product]
  (let [query (sql/format (insert-to-dto product))]
    (jdbc/execute! db/conn query)))

(defn- select-dto
  [title]
  {:select [:*]
   :from   [:products]
   :where [:= :title title]})

(defn view-products
  [product]
  (let [query (sql/format (select-dto product))]
    (jdbc/execute! db/conn query)))

(defn- list-dto
  []
  {:select [:*]
   :from   [:products]})

(defn list-products
  [product]
  (let [query (sql/format (list-dto))]
    (jdbc/execute! db/conn query)))

(defn- update-dto
  [data]
  (let [title (:title data)
        desc (:description data)
        price (:price data)
        image (:image data)
        amount (:amount data)
        id (:id data)]
    (-> (h/update :products)
        (h/set {:title title
                :description desc
                :price price
                :image image
                :amount amount})
        (h/where [:= :id id]))))

(defn update-products
  [product]
  (let [query (sql/format (update-dto product))]
    (jdbc/execute! db/conn query)))

(defn- delete-dto
  [product]
  (-> (h/delete-from :products)
      (h/where [:= :id (:id product)])))

(defn delete-products
  [product]
  (let [query (sql/format (delete-dto product))]
    (jdbc/execute! db/conn query)))
