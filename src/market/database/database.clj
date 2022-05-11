(ns market.database.database
  (:require [next.jdbc :as jdbc]))

(def db {:dbtype "postgresql" :dbname "market" :user "postgres" :username "postgres" :password "postgres"})
(def conn (jdbc/get-datasource db))

(defn create-tables
  [tables]
  (doseq [table tables]
    (jdbc/execute! conn table)))

